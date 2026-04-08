package pe.com.master.machines.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import pe.com.master.machines.network.model.model.ActivePdaNetwork

@Serializable
data class ResponseSearchBarcodeNetwork(
    @SerialName("data")
    val data: ActivePdaNetwork? = null,
    @SerialName("hijos")
    val listChildren: List<ActivePdaNetwork>? = null,
    @SerialName("padre")
    val father: ActivePdaNetwork? = null,
    @SerialName("mensaje")
    val message: String? = null,
    @SerialName("ok")
    val ok: Boolean? = null
)