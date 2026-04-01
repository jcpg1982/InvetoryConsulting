package pe.com.master.machines.root_navigation.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import pe.com.master.machines.design.utils.Utils.horizontalSlideTransition
import pe.com.master.machines.design.components.custom.StatusScreen
import pe.com.master.machines.design.components.dialogs.LoadingDialog
import pe.com.master.machines.login.ui.LoginScreen
import pe.com.master.machines.main_drawer_navigation.ui.MainDrawerNavigationWrapper
import pe.com.master.machines.model.sealed.MainRoutes
import pe.com.master.machines.root_navigation.viewmodel.RootViewModel

@Composable
fun RootNavigationWrapper(
    modifier: Modifier = Modifier,
    viewModel: RootViewModel = hiltViewModel()
) {
    val isAuthorized by viewModel.isDeviceAuthorized.collectAsState()
    val mustUpdate by viewModel.mustUpdate.collectAsState()
    val isForceUpdate by viewModel.isForceUpdate.collectAsState()
    val updateIgnored by viewModel.updateIgnored.collectAsState()
    val deviceId by viewModel.deviceId.collectAsState()
    val context = LocalContext.current

    val onUpdateClick = {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${context.packageName}"))
            context.startActivity(intent)
        } catch (e: Exception) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${context.packageName}"))
            context.startActivity(intent)
        }
    }

    when (isAuthorized) {
        null -> LoadingDialog()

        false -> {
            StatusScreen(
                title = "DISPOSITIVO NO AUTORIZADO",
                description = "Este equipo no tiene permiso para acceder. ID del dispositivo:\n$deviceId\n\nPor favor, contacte con el administrador.",
                icon = Icons.Default.Block
            )
        }

        true -> {
            if (mustUpdate && !updateIgnored) {
                if (isForceUpdate) {
                    StatusScreen(
                        title = "ACTUALIZACIÓN OBLIGATORIA",
                        description = "Debes actualizar la aplicación para continuar.",
                        icon = Icons.Default.SystemUpdate,
                        buttonLabel = "ACTUALIZAR AHORA",
                        onClick = onUpdateClick
                    )
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        StatusScreen(
                            title = "NUEVA VERSIÓN DISPONIBLE",
                            description = "Hay una actualización disponible con mejoras. ¿Deseas actualizar ahora?",
                            icon = Icons.Default.SystemUpdate,
                            buttonLabel = "ACTUALIZAR",
                            onClick = onUpdateClick
                        )
                        
                        TextButton(
                            onClick = { viewModel.ignoreUpdate() },
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 32.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp)
                        ) {
                            Text("MÁS TARDE")
                        }
                    }
                }
            } else {
                RootNavContent(modifier)
            }
        }
    }
}

@Composable
fun RootNavContent(modifier: Modifier) {
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
