package pe.com.master.machines.model.model

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val userId: Int,
    val userName: String,
    val listInventories: List<Inventory>
)