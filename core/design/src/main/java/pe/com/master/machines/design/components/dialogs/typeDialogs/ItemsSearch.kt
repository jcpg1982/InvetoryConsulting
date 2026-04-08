package pe.com.master.machines.design.components.dialogs.typeDialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pe.com.master.machines.design.components.list.LazyColumnCustom
import pe.com.master.machines.design.components.text.SearchText
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.ContentInsetSixteen

@Composable
inline fun <reified T> ItemsSearch(
    hintSearch: String,
    listItems: List<T>,
    primaryColor: Color,
    colorText: Color,
    crossinline onItemsCallback: (Int, T) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var filteredList by remember { mutableStateOf(listItems) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(ContentInsetSixteen)
    ) {
        SearchText(
            hintSearch = hintSearch,
            value = searchText,
            onValueChange = { searchText = it },
            maxCharacter = 100,
            primaryColor = primaryColor,
            colorText = colorText,
            onSearch = { query ->
                filteredList = if (query.isEmpty()) {
                    listItems
                } else {
                    listItems.filter { data ->
                        data.toString().lowercase().contains(query.lowercase())
                    }
                }
            }
        )

        LazyColumnCustom(
            listItems = filteredList,
            primaryColor = primaryColor,
            onItemsCallback = { pos, data -> onItemsCallback(pos, data) }
        )
    }
}
