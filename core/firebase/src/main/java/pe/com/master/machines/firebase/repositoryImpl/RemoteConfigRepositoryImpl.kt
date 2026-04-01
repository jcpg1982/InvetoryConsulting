package pe.com.master.machines.firebase.repositoryImpl

import android.util.Log
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.json.Json
import pe.com.master.machines.firebase.model.RemoteDeviceConfigFirebase
import pe.com.master.machines.firebase.repository.RemoteConfigRepository
import javax.inject.Inject

class RemoteConfigRepositoryImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) : RemoteConfigRepository {

    private val TAG = RemoteConfigRepositoryImpl::class.java.simpleName
    private val json = Json { ignoreUnknownKeys = true }

    init {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

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

    override fun getDeviceConfigs(): Flow<List<RemoteDeviceConfigFirebase>> = callbackFlow {
        trySend(currentListDeviceConfigs())
        val listener = object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                if (configUpdate.updatedKeys.contains("device_configs")) {
                    remoteConfig.activate().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            trySend(currentListDeviceConfigs())
                        }
                    }
                }
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                Log.e(TAG, "Error en actualización en tiempo real", error)
            }
        }

        val registration = remoteConfig.addOnConfigUpdateListener(listener)

        awaitClose {
            registration.remove()
        }
    }

    fun currentListDeviceConfigs(): List<RemoteDeviceConfigFirebase> {
        val jsonString = getString("device_configs")
        return if (jsonString.isEmpty()) {
            emptyList()
        } else {
            try {
                json.decodeFromString<List<RemoteDeviceConfigFirebase>>(jsonString)
            } catch (e: Exception) {
                Log.e(TAG, "Error parsing device_configs", e)
                emptyList()
            }
        }
    }
}