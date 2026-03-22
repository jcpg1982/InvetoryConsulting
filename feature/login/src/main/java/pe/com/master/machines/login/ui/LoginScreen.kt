package pe.com.master.machines.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pe.com.master.machines.design.components.text.CustomTextInput
import pe.com.master.machines.design.components.text.TextClickButton
import pe.com.master.machines.design.components.topBar.BasicTopBar
import pe.com.master.machines.design.theme.ContentInsetSixteen

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit
) {

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
                        value = "",
                        hintText = "Ingrese su DNI",
                        maxLines = 1,
                        onTextValueChange = {}
                    )

                    Spacer(modifier = Modifier.height(ContentInsetSixteen))

                    TextClickButton(
                        textButton = "Iniciar Sesión",
                        modifier = Modifier.wrapContentWidth(),
                        onClick = { }
                    )
                }
            )
        }
    )

}