package pe.com.master.machines.model.response.model

data class User(
    val id: String,
    val profile: Profile,
    val rbac: Rbac
)
