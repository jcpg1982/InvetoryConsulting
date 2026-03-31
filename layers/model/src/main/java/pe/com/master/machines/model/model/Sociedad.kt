package pe.com.master.machines.model.model

import kotlinx.serialization.Serializable

@Serializable
data class Sociedad(
    val id: Int,
    val sociedadName: String,
    val listInventories: List<Inventory>,
)