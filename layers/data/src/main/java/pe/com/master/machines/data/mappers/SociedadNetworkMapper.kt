package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.model.Sociedad
import pe.com.master.machines.network.model.model.SociedadNetwork

fun SociedadNetwork?.asModelSociedad() = Sociedad(
    id = this?.id ?: -1,
    sociedadName = this?.sociedadName.orEmpty(),
    listInventories = this?.listInventories.asListInventory(),
)

fun List<SociedadNetwork>?.asListSociedad() = this?.map { it.asModelSociedad() } ?: listOf()
