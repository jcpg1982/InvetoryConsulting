package pe.com.master.machines.main_drawer_navigation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.coroutines.launch
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import pe.com.master.machines.design.components.drawer.ContentDrawer
import pe.com.master.machines.design.components.topBar.TopBarHome
import pe.com.master.machines.design.utils.Utils.horizontalSlideTransition
import pe.com.master.machines.home.ui.HomeScreen
import pe.com.master.machines.model.model.Data
import pe.com.master.machines.model.model.Inventory
import pe.com.master.machines.model.sealed.MainRoutes

@Composable
fun MainDrawerNavigationWrapper(
    data: Data,
    onNavigateToLogin: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val listItems = remember(data.listSociedades) { data.listSociedades }
    var sociedad by remember { mutableStateOf(listItems.firstOrNull()) }
    var idSociedad by remember { mutableStateOf(sociedad?.id) }
    var inventory by remember { mutableStateOf(sociedad?.listInventories?.firstOrNull()) }
    var idInventory by remember { mutableStateOf(inventory?.id) }

    var title by remember { mutableStateOf(sociedad?.sociedadName.orEmpty()) }
    var subTitle by remember { mutableStateOf(inventory?.inventoryName.orEmpty()) }

    val scope = rememberCoroutineScope()
    val backStack = rememberNavBackStack(
        configuration = navSavedStateConfigurationMainDrawer,
        MainRoutes.HomeRoute
    )

    ModalNavigationDrawer(
        modifier = Modifier.fillMaxSize(),
        drawerState = drawerState,
        drawerContent = {
            ContentDrawer(
                listItems = listItems,
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                data = data,
                onItemSelected = { sociedadId, inventoryId ->
                    scope.launch {
                        drawerState.close()
                        if (inventoryId != idInventory) {
                            val sociedad = data.listSociedades.find { it.id == sociedadId }
                            val inventory = sociedad?.listInventories?.find { it.id == inventoryId }
                            title = sociedad?.sociedadName.orEmpty()
                            subTitle = inventory?.inventoryName.orEmpty()
                            idSociedad = sociedadId
                            idInventory = inventoryId
                        }
                    }
                },
                onClosedSession = {
                    onNavigateToLogin()
                }
            )
        },
        content = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopBarHome(
                        title = "$title\n$subTitle",
                        onClickNavigation = {
                            scope.launch {
                                if (drawerState.isOpen) drawerState.close()
                                else drawerState.open()
                            }
                        }
                    )
                },
                content = { paddingValues ->
                    NavDisplay(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        entryProvider = entryProvider {
                            entry<MainRoutes.HomeRoute> {
                                HomeScreen(
                                    sociedadId = idSociedad ?: -1,
                                    inventoryId = idInventory ?: -1
                                )
                            }
                        },
                        transitionSpec = { horizontalSlideTransition(false) },
                        popTransitionSpec = { horizontalSlideTransition(true) }
                    )
                }
            )
        }
    )

}

val navSavedStateConfigurationMainDrawer = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(MainRoutes.HomeRoute::class)
        }
    }
}