package pe.com.master.machines.network.model.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DataNetwork(
    @SerializedName("id_usuario")
    val userId: Int? = null,
    @SerializedName("nombre")
    val userName: String? = null,
    @SerializedName("inventarios")
    val listInventories: List<InventoryNetwork>? = null
)