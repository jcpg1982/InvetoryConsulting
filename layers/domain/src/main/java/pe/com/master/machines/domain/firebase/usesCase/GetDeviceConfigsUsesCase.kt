package pe.com.master.machines.domain.firebase.usesCase

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.Resource
import pe.com.master.machines.model.model.RemoteDeviceConfig

interface GetDeviceConfigsUsesCase {
    operator fun invoke(): Flow<Resource<List<RemoteDeviceConfig>>>
}