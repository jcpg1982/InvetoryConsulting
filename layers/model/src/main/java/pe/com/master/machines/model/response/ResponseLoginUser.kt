package pe.com.master.machines.model.response

import pe.com.master.machines.model.model.Data

data class ResponseLoginUser(
    val ok: Boolean,
    val message: String,
    val data: Data,
)