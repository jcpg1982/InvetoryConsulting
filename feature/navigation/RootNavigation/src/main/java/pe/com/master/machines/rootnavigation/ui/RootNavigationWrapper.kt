package pe.com.master.machines.rootnavigation.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import pe.com.master.machines.login.ui.LoginScreen
import pe.com.master.machines.model.sealed.MainRoutes

@Composable
fun RootNavigationWrapper(
    modifier: Modifier = Modifier
) {

    val backStack = rememberNavBackStack(
        configuration = navSavedStateConfiguration, MainRoutes.LoginRoute
    )

    val currentRoute = remember(backStack) {
        backStack.lastOrNull()
    }

    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<MainRoutes.LoginRoute> {
                LoginScreen(
                    onNavigateToHome = {
                        /*backStack.clear()
                        backStack.add(HomeBaseRoute)*/
                    }
                )
            }
            /*entry<HomeBaseRoute> {
                HomeBaseScreen(
                    onNavigateToDetailAccount = { account ->
                        backStack.add(DetailAccountRoute(account))
                    })
            }
            entry<DetailAccountRoute> { key ->
                DetailAccountScreen(
                    account = key.bankAccount,
                    onNavigateToBack = { backStack.removeLastOrNull() })
            }*/
        },
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { it }, animationSpec = tween(500)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { -it }, animationSpec = tween(500)
            )
        },
        popTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it }, animationSpec = tween(500)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it }, animationSpec = tween(500)
            )
        }
    )
}

val navSavedStateConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(MainRoutes.LoginRoute::class)
            subclass(MainRoutes.MainDrawerRoute::class)
        }
    }
}