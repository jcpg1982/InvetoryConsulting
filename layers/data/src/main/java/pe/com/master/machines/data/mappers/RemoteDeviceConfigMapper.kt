package pe.com.master.machines.data.mappers

import pe.com.master.machines.firebase.model.RemoteDeviceConfigFirebase
import pe.com.master.machines.model.model.RemoteDeviceConfig

fun RemoteDeviceConfigFirebase.asModelRemoteDeviceConfig() = RemoteDeviceConfig(
    deviceId = deviceId.orEmpty(),
    versionMajor = versionMajor ?: -1,
    versionMinor = versionMinor ?: -1,
    forceUpdate = forceUpdate ?: false,
    isActive = isActive ?: false
)

fun List<RemoteDeviceConfigFirebase>.asListRemoteDeviceConfig() =
    map { it.asModelRemoteDeviceConfig() }