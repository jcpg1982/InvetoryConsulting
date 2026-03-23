package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.model.Inventory
import pe.com.master.machines.network.model.model.InventoryNetwork

fun InventoryNetwork?.asModelInventory() = Inventory(
    id = this?.id ?: -1,
    inventoryName = this?.inventoryName.orEmpty(),
)

fun List<InventoryNetwork>?.asListInventory() = this?.map { it.asModelInventory() } ?: listOf()
