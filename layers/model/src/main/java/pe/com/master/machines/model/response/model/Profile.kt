package pe.com.master.machines.model.response.model

data class Profile(
    val countriesLogin: List<Country>,
    val countryLogin: Country,
    val language: String,
    val languages: String,
    val userName: String
)
