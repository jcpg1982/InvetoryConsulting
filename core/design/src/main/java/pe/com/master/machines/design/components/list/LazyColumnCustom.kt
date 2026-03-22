package pe.com.master.machines.design.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.components.row.ItemDialogRow
import pe.com.master.machines.design.theme.ContentInsetSixteen
import pe.com.master.machines.design.theme.ContentInsetThreeHundred
import pe.com.master.machines.design.theme.ContentInsetTwo

@Composable
fun <T> LazyColumnCustom(
    listItems: List<T>,
    primaryColor: Color,
    onItemsCallback: (Int, T) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = ContentInsetSixteen)
            .heightIn(max = ContentInsetThreeHundred),
        verticalArrangement = Arrangement.spacedBy(ContentInsetTwo)
    ) {
        listItems.forEachIndexed { index, data ->
            item {
                when (data) {
                    is String -> {
                        ItemDialogRow(
                            text = data,
                            color = primaryColor
                        ) {
                            onItemsCallback(index, data)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLazyColumnCustom() {
    LazyColumnCustom(
        listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5"),
        primaryColor = Color.Blue
    ) { pos, data -> }
}
