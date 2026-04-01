package pe.com.master.machines.data.firebase.repositoryImpl

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.Resource
import pe.com.master.machines.common.toErrorType
import pe.com.master.machines.data.firebase.repository.FirebaseDataRepository
import pe.com.master.machines.data.mappers.asListRemoteDeviceConfig
import pe.com.master.machines.firebase.repository.RemoteConfigRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseDataRepositoryImpl @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository
) : FirebaseDataRepository {

    override fun getDeviceConfigs() =
        remoteConfigRepository.getDeviceConfigs().map { res ->
            when (res) {
                is Resource.Success -> Resource.Success(res.data.asListRemoteDeviceConfig())
                is Resource.Error -> Resource.Error(res.errorType)
            }
        }
            .catch { e ->
                emit(Resource.Error(e.toErrorType()))
            }

}