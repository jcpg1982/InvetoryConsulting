package pe.com.master.machines.model.response

import pe.com.master.machines.model.model.ActivePda

data class ResponseSearchBarcode(
    val data: ActivePda,
    val message: String,
    val ok: Boolean
)