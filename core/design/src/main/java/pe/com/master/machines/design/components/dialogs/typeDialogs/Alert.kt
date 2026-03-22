package pe.com.master.machines.design.components.dialogs.typeDialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pe.com.master.machines.design.components.bottoms.ActionButtonsDialog
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.components.text.MessageDialog
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.ContentInsetSixteen

@Composable
fun Alert(
    title: String,
    message: String,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    onPositiveCallback: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .background(color = ColorWhite)
            .padding(ContentInsetSixteen)
    ) {
        CustomText(text = title)
        MessageDialog(
            message = message,
            modifier = Modifier.fillMaxWidth()
        )
        ActionButtonsDialog(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = ContentInsetSixteen),
            textPositiveButton = textPositiveButton,
            textColorPositiveButton = textColorPositiveButton,
            backgroundColorPositiveButton = backgroundColorPositiveButton,
            onPositiveCallback = onPositiveCallback,
        )
    }
}
