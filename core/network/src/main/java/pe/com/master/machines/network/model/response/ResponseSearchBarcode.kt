package pe.com.master.machines.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import pe.com.master.machines.network.model.model.ActivePdaNetwork

@Serializable
data class ResponseSearchBarcode(
    @SerialName("data")
    val data: ActivePdaNetwork,
    @SerialName("mensaje")
    val message: String,
    @SerialName("ok")
    val ok: Boolean
)