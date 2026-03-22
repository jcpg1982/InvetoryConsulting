package pe.com.master.machines.model.response.model

data class Data(
    val accessToken: String,
    val expiresIn: String,
    val refreshToken: String,
    val tokenType: String,
    val user: User
)
