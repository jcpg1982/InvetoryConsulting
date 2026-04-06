package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.response.ResponseSearchBarcode
import pe.com.master.machines.network.model.response.ResponseSearchBarcodeNetwork

fun ResponseSearchBarcodeNetwork?.asModelResponseSearchBarcode() = ResponseSearchBarcode(
    data = this?.data.asModelActivePda(),
    listChildren = this?.listChildren.asListActivePda(),
    message = this?.message.orEmpty(),
    ok = this?.ok ?: false,
)