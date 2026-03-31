package pe.com.master.machines.network.model.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataNetwork(
    @SerialName("id_usuario")
    val userId: Int? = null,
    @SerialName("nombre")
    val userName: String? = null,
    @SerialName("sociedades")
    val listSociedades: List<SociedadNetwork>? = null
)