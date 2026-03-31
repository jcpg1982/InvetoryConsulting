package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.model.Data
import pe.com.master.machines.network.model.model.DataNetwork

fun DataNetwork?.asModelData() = Data(
    documentNumber = this?.documentNumber.orEmpty(),
    userName = this?.userName.orEmpty(),
    listSociedades = this?.listSociedades.asListSociedad(),
)

