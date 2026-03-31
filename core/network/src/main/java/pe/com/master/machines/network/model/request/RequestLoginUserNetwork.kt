package pe.com.master.machines.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginUserNetwork(
    @SerialName("nro_documento")
    val documentNumber: String? = null,
)
