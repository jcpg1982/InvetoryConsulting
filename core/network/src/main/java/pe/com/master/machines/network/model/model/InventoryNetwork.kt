package pe.com.master.machines.network.model.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InventoryNetwork(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("nom_inventario")
    val inventoryName: String? = null,
    @SerialName("cod_barra")
    val sizeCodBarra: Int? = null
)