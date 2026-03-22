package pe.com.master.machines.model.sealed

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class MainRoutes : NavKey {

    @Serializable
    object LoginRoute : MainRoutes()

    @Serializable
    object MainDrawerRoute : MainRoutes()

}