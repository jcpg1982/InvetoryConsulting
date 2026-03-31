package pe.com.master.machines.model.sealed

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import pe.com.master.machines.model.model.Data

sealed class MainRoutes : NavKey {

    @Serializable
    data object LoginRoute : MainRoutes()

    @Serializable
    data class MainDrawerRoute(val data: Data) : MainRoutes()

    @Serializable
    data object HomeRoute : MainRoutes()

    @Serializable
    data class FullImageRoute(val imageUrl: String) : MainRoutes()

}
