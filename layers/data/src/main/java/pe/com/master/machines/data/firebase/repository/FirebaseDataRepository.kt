package pe.com.master.machines.data.firebase.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.Resource
import pe.com.master.machines.model.model.RemoteDeviceConfig

interface FirebaseDataRepository {
    fun getDeviceConfigs(): Flow<Resource<List<RemoteDeviceConfig>>>
}