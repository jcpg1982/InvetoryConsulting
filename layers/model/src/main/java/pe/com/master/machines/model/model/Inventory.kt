package pe.com.master.machines.model.model

import kotlinx.serialization.Serializable

@Serializable
data class Inventory(
    val id: Int,
    val inventoryName: String
)