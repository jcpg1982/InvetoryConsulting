package pe.com.master.machines.design.components.dialogs.typeDialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pe.com.master.machines.design.components.list.LazyColumnCustom
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.ContentInsetSixteen

@Composable
inline fun <reified T> Items(
    title: String,
    primaryColor: Color,
    listItems: List<T>,
    crossinline onItemsCallback: (Int, T) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(ContentInsetSixteen)
    ) {
        CustomText(text = title)
        LazyColumnCustom(
            listItems = listItems,
            primaryColor = primaryColor,
            onItemsCallback = { pos, data -> onItemsCallback(pos, data) }
        )
    }
}