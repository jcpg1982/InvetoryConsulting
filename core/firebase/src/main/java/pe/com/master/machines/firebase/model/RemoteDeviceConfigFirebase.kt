package pe.com.master.machines.firebase.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteDeviceConfigFirebase(
    val deviceId: String? = null,
    val versionMajor: Int? = null,
    val versionMinor: Int? = null,
    val forceUpdate: Boolean? = null,
    val isActive: Boolean? = null
)