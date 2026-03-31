package pe.com.master.machines.network.utils

object Constants {
    const val UNION_BASE_URL = "robocon/api/"
    const val BASE_URL = "https://api.dmycm.com.pe/"
    const val LOGIN_USER = UNION_BASE_URL + "cliente/login/"
    const val SEARCH_ACTIVE =
        UNION_BASE_URL + "cliente/sociedad/{id_sociedad}/inventario/{id_inventario}/activos/{cod_barra}"
}