package pe.com.master.machines.model.request.model

data class User(
    val pass: String = "",
    val profile: Profile = Profile(),
    val usrCode: String = ""
)
