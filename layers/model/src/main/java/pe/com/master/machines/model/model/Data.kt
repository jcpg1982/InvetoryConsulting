package pe.com.master.machines.model.model

import kotlinx.serialization.Serializable


@Serializable
data class Data(
    val documentNumber: String,
    val userName: String,
    val listSociedades: List<Sociedad>
)