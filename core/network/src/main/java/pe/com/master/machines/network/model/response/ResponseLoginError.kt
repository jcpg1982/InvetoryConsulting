package pe.com.master.machines.network.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginError(
    @SerializedName("data")
    val `data`: String? = null,
    @SerializedName("mensaje")
    val message: String? = null,
    @SerializedName("ok")
    val ok: Boolean? = null
)