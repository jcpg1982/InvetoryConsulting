package pe.com.master.machines.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.com.master.machines.design.components.dialogs.DialogConfirm
import pe.com.master.machines.design.components.dialogs.LoadingDialog
import pe.com.master.machines.design.components.text.CustomTextInput
import pe.com.master.machines.design.components.text.TextClickButton
import pe.com.master.machines.design.components.topBar.BasicTopBar
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.ContentInsetSixteen
import pe.com.master.machines.login.state.LoginState
import pe.com.master.machines.login.viewmodel.LoginViewmodel
import pe.com.master.machines.model.model.Data

@Composable
fun LoginScreen(
    onNavigateToHome: (Data) -> Unit,
    viewmodel: LoginViewmodel = hiltViewModel()
) {

    val loginUserState by viewmodel.loginUserState.collectAsStateWithLifecycle()
    val documentNumber by viewmodel.documentNumber.collectAsStateWithLifecycle()

    when (val state = loginUserState) {
        LoginState.First -> {}
        is LoginState.Error -> DialogConfirm(
            title = "Error",
            message = state.message,
            isCancelable = false,
            textPositiveButton = "Reintentar",
            textColorPositiveButton = MaterialTheme.colorScheme.onPrimary,
            backgroundColorPositiveButton = ColorWhite,
            onPositiveCallback = { viewmodel.getLoginUser() },
            textNegativeButton = "Cerrar",
            textColorNegativeButton = MaterialTheme.colorScheme.onSecondary,
            backgroundColorNegativeButton = ColorWhite,
            onNegativeCallback = { viewmodel.resetLoginState() },
            onDismissDialog = {}
        )

        LoginState.Loading -> LoadingDialog()
        is LoginState.SuccessLogin -> {
            onNavigateToHome(state.data)
            viewmodel.resetLoginState()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BasicTopBar(
                title = "Inicia sesion"
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = ContentInsetSixteen),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {

                    Spacer(modifier = Modifier.height(ContentInsetSixteen))

                    CustomTextInput(
                        modifier = Modifier.fillMaxWidth(),
                        value = documentNumber,
                        hintText = "Ingrese su DNI",
                        maxCharacter = 10,
                        maxLines = 1,
                        onTextValueChange = {
                            viewmodel.updateDocumentNumber(it)
                        }
                    )

                    Spacer(modifier = Modifier.height(ContentInsetSixteen))

                    TextClickButton(
                        textButton = "Iniciar Sesión",
                        modifier = Modifier.wrapContentWidth(),
                        onClick = { viewmodel.getLoginUser() }
                    )
                }
            )
        }
    )

}