package pe.com.master.machines.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginError(
    @SerialName("data")
    val `data`: String? = null,
    @SerialName("mensaje")
    val message: String? = null,
    @SerialName("ok")
    val ok: Boolean? = null
)