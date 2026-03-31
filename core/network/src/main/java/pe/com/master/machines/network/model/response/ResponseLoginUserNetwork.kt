package pe.com.master.machines.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import pe.com.master.machines.network.model.model.DataNetwork

@Serializable
data class ResponseLoginUserNetwork(
    @SerialName("ok")
    val ok: Boolean? = null,
    @SerialName("mensaje")
    val message: String? = null,
    @SerialName("data")
    val data: DataNetwork? = null,
)