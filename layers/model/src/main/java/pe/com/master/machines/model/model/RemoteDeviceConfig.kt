package pe.com.master.machines.model.model

data class RemoteDeviceConfig(
    val deviceId: String,
    val versionMajor: Int,
    val versionMinor: Int,
    val forceUpdate: Boolean,
    val isActive: Boolean
)