package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.request.RequestLoginUser
import pe.com.master.machines.network.model.request.RequestLoginUserNetwork

fun RequestLoginUser.asModelRequestLoginUserNetwork() = RequestLoginUserNetwork(
    documentNumber = this.documentNumber
)