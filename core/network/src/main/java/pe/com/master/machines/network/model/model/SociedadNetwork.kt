package pe.com.master.machines.network.model.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SociedadNetwork(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("nom_sociedad")
    val sociedadName: String? = null,
    @SerialName("inventarios")
    val listInventories: List<InventoryNetwork>? = null,
)