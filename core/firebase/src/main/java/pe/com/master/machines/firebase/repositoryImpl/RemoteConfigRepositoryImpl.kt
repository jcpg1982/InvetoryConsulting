package pe.com.master.machines.firebase.repositoryImpl

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pe.com.master.machines.firebase.repository.RemoteConfigRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteConfigRepositoryImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) : RemoteConfigRepository {

    private val TAG = RemoteConfigRepositoryImpl::class.java.simpleName

    init {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600 // 1 hour for standard fetch
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        
        // Initial fetch and activate
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Config params updated: ${task.result}")
                } else {
                    Log.e(TAG, "Config fetch failed")
                }
            }
    }

    override fun getString(key: String): String = remoteConfig.getString(key)

    override fun getBoolean(key: String): Boolean = remoteConfig.getBoolean(key)

    override fun getLong(key: String): Long = remoteConfig.getLong(key)

    override fun getDouble(key: String): Double = remoteConfig.getDouble(key)

    override fun getStringFlow(key: String): Flow<String> = callbackFlow {
        // Send initial value
        trySend(remoteConfig.getString(key))

        val listener = object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                if (configUpdate.updatedKeys.contains(key)) {
                    remoteConfig.activate().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            trySend(remoteConfig.getString(key))
                        }
                    }
                }
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                Log.e(TAG, "Config update error", error)
            }
        }

        val registration = remoteConfig.addOnConfigUpdateListener(listener)

        awaitClose {
            registration.remove()
        }
    }
}