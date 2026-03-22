package pe.com.master.machines.design.components.dialogs.typeDialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pe.com.master.machines.design.components.list.LazyColumnCustom
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.components.text.MessageDialog
import pe.com.master.machines.design.components.text.TextClickButton
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.ContentInsetSixteen

@Composable
inline fun <reified T> ItemsAlert(
    title: String,
    message: String,
    listItems: List<T>,
    primaryColor: Color,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    crossinline onPositiveCallback: () -> Unit,
    crossinline onItemsCallback: (Int, T) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(ContentInsetSixteen)
    ) {
        CustomText(text = title)
        MessageDialog(message = message)
        LazyColumnCustom(
            listItems = listItems,
            primaryColor = primaryColor,
            onItemsCallback = { pos, data -> onItemsCallback(pos, data) }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = ContentInsetSixteen),
            contentAlignment = Alignment.CenterEnd
        ) {
            TextClickButton(
                modifier = Modifier.wrapContentWidth(),
                textButton = textPositiveButton,
                textColorButton = textColorPositiveButton,
                backgroundColorButton = backgroundColorPositiveButton
            ) { onPositiveCallback() }
        }
    }
}