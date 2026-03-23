package pe.com.master.machines.design.components.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ColorBlack
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.model.model.Data
import pe.com.master.machines.model.model.Inventory

@Composable
fun ContentDrawer(
    listItems: List<Inventory>,
    data: Data,
    modifier: Modifier = Modifier,
    onItemSelected: (Inventory) -> Unit,
    onClosedSession: () -> Unit,
) {

    var selectedItem by remember {
        mutableStateOf(listItems.firstOrNull())
    }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        HeaderDrawer(data = data)
        LazyColumn(
            Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            items(listItems) { item ->
                val isSelected = item == selectedItem
                val (colorBackGround, colorText) = if (isSelected) Pair(
                    ColorBlack.copy(alpha = 0.3f),
                    ColorWhite
                )
                else Pair(
                    ColorWhite,
                    ColorBlack
                )
                CustomText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorBackGround)
                        .weight(1f)
                        .padding(ContentInsetEight)
                        .clickable { onItemSelected(item) },
                    text = item.inventoryName,
                    color = colorText,
                    maxLines = 2
                )
            }
            item {
                CustomText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = ColorWhite)
                        .weight(1f)
                        .padding(ContentInsetEight)
                        .clickable { onClosedSession() },
                    text = "Cerrar Sesión",
                    color = ColorBlack,
                    maxLines = 2
                )
            }
        }
        FooterDrawer()
    }
}

val listItems = listOf(
    Inventory(
        id = 0,
        inventoryName = "primero"
    ),
    Inventory(
        id = 1,
        inventoryName = "segundo"
    ),
    Inventory(
        id = 2,
        inventoryName = "tercero"
    ),
    Inventory(
        id = 3,
        inventoryName = "cuarto"
    )
)

@Preview
@Composable
fun PreviewDarkContentDrawer() {
    ConsultaInventarioTheme(
        darkTheme = true
    ) {
        ContentDrawer(
            listItems = listItems,
            data = Data(
                userId = 1982,
                userName = "Jak Motero",
                listInventories = listOf()
            ),
            onItemSelected = {},
            onClosedSession = {},
        )
    }
}

@Preview
@Composable
fun PreviewLightContentDrawer() {
    ConsultaInventarioTheme(
        darkTheme = false
    ) {
        ContentDrawer(
            listItems = listItems,
            data = Data(
                userId = 1982,
                userName = "Jak Motero",
                listInventories = listOf()
            ),
            onItemSelected = {},
            onClosedSession = {},
        )
    }
}
