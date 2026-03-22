package pe.com.master.machines.network.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import pe.com.master.machines.network.model.model.DataNetwork
import pe.com.master.machines.network.model.model.InventoryNetwork

@Serializable
data class ResponseLoginUserNetwork(
    @SerializedName("ok")
    val ok: Boolean? = null,
    @SerializedName("mensaje")
    val message: String? = null,
    @SerializedName("data")
    val data: DataNetwork? = null,
)