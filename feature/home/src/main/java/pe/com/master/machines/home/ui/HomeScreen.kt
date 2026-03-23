package pe.com.master.machines.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pe.com.master.machines.design.components.text.CustomTextInput
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetSixteen

@Composable
fun HomeScreen(
    inventoryId: Int
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = ContentInsetSixteen),
        content = {

            Spacer(modifier = Modifier.height(ContentInsetEight))

            CustomTextInput(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                hintText = "Ingrese el código",
                maxCharacter = 50
            )

        }
    )

}