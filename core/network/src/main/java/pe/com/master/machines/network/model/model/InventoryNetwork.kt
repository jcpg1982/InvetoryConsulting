package pe.com.master.machines.network.model.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class InventoryNetwork(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("nom_inventario")
    val inventoryName: String? = null
)