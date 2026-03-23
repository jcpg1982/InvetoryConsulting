package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.response.ResponseLoginUser
import pe.com.master.machines.network.model.response.ResponseLoginUserNetwork

fun ResponseLoginUserNetwork?.asModelResponseLoginUser() = ResponseLoginUser(
    ok = this?.ok ?: false,
    message = this?.message.orEmpty(),
    data = this?.data.asModelData(),
)