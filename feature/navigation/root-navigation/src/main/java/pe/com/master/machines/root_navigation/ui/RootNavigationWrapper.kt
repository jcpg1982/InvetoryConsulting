package pe.com.master.machines.root_navigation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import pe.com.master.machines.design.utils.Utils.horizontalSlideTransition
import pe.com.master.machines.full_image.ui.FullImageScreen
import pe.com.master.machines.login.ui.LoginScreen
import pe.com.master.machines.main_drawer_navigation.ui.MainDrawerNavigationWrapper
import pe.com.master.machines.model.sealed.MainRoutes

@Composable
fun RootNavigationWrapper(
    modifier: Modifier = Modifier
) {

    val backStack = rememberNavBackStack(
        configuration = navSavedStateConfigurationRoot, MainRoutes.LoginRoute
    )

    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<MainRoutes.LoginRoute> {
                LoginScreen(
                    onNavigateToHome = {
                        backStack.clear()
                        backStack.add(MainRoutes.MainDrawerRoute(it))
                    }
                )
            }
            entry<MainRoutes.MainDrawerRoute> { key ->
                val data = key.data
                MainDrawerNavigationWrapper(
                    data = data,
                    onNavigateToLogin = {
                        backStack.clear()
                        backStack.add(MainRoutes.LoginRoute)
                    },
                    onNavigateToFullImage = {
                        backStack.add(MainRoutes.FullImageRoute(it))
                    }
                )
            }
            entry<MainRoutes.FullImageRoute> {
                FullImageScreen(
                    imageUrl = it.imageUrl,
                    onNavigateToBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        },
        transitionSpec = { horizontalSlideTransition(false) },
        popTransitionSpec = { horizontalSlideTransition(true) }
    )
}

val navSavedStateConfigurationRoot = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(MainRoutes.LoginRoute::class)
            subclass(MainRoutes.MainDrawerRoute::class)
        }
    }
}