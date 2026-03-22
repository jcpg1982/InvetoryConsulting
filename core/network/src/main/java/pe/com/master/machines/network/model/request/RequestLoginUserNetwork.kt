package pe.com.master.machines.network.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginUserNetwork(
    @SerializedName("nro_documento")
    val documentNumber: String? = null,
)
