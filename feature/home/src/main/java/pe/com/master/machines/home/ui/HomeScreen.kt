package pe.com.master.machines.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.com.master.machines.design.components.dialogs.DialogAlert
import pe.com.master.machines.design.components.dialogs.LoadingDialog
import pe.com.master.machines.design.components.text.SearchText
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetSixteen
import pe.com.master.machines.home.state.HomeState
import pe.com.master.machines.home.viewmodel.HomeViewmodel
import pe.com.master.machines.model.model.ActivePda

@Composable
fun HomeScreen(
    sociedadId: Int,
    inventoryId: Int,
    viewModel: HomeViewmodel = hiltViewModel()
) {

    var messageError by rememberSaveable { mutableStateOf("") }
    var messageLoading by rememberSaveable { mutableStateOf("") }
    var activePda = remember<ActivePda?> { null }

    LaunchedEffect(Unit) {
        viewModel.homeState.collect { homeState ->
            when (homeState) {
                is HomeState.Error -> {
                    activePda = null
                    messageLoading = ""
                    messageError = homeState.message
                }

                HomeState.Loading -> {
                    activePda = null
                    messageError = ""
                    messageLoading = "Loading"
                }

                is HomeState.SuccessSearch -> {
                    messageLoading = ""
                    messageError = ""
                    activePda = homeState.data
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = ContentInsetSixteen),
        content = {

            Spacer(modifier = Modifier.height(ContentInsetEight))

            SearchText(
                hintSearch = "Ingresar código",
                maxCharacter = 100,
                primaryColor = Color.Red,
                colorText = Color.Black,
                onMessageSearch = {
                    viewModel.getSearchActivePda(sociedadId, inventoryId, it)
                }
            )

            if (activePda != null) {

            }
        }
    )

    if (messageError.isNotBlank()) {
        DialogAlert(
            title = "Error en el servidor",
            message = messageError,
            textPositiveButton = "Aceptar",
            textColorPositiveButton = Color.Blue,
            backgroundColorPositiveButton = Color.White,
            onPositiveCallback = {
                messageError = ""
            },
            onDismissDialog = {
                messageError = ""
            }
        )
    }

    if (messageLoading.isNotBlank()) {
        LoadingDialog()
    }

}