package pe.com.master.machines.design.components.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import pe.com.master.machines.design.theme.DynamicTextFourteen
import pe.com.master.machines.model.model.Data
import pe.com.master.machines.model.model.Inventory
import pe.com.master.machines.model.model.Sociedad

@Composable
fun ContentDrawer(
    listItems: List<Sociedad>,
    data: Data,
    modifier: Modifier = Modifier,
    onItemSelected: (Int, Int) -> Unit,
    onClosedSession: () -> Unit,
) {

    var selectedSociedad by remember {
        mutableStateOf(listItems.first())
    }

    var selectedInventory by remember(selectedSociedad) {
        mutableStateOf(selectedSociedad.listInventories.first())
    }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        HeaderDrawer(data = data)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            content = {
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .weight(1f),
                    content = {
                        items(listItems) { item ->
                            val isSelected = item == selectedSociedad
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
                                    .clickable { selectedSociedad = item },
                                text = item.sociedadName,
                                color = colorText,
                                maxLines = 3,
                                fontSize = DynamicTextFourteen
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
                                fontSize = DynamicTextFourteen,
                                maxLines = 2
                            )
                        }
                    }
                )
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .weight(1f),
                    content = {
                        items(selectedSociedad.listInventories) { item ->
                            val isSelected = item == selectedInventory
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
                                    .clickable {
                                        onItemSelected(
                                            selectedSociedad.id,
                                            selectedInventory.id
                                        )
                                    },
                                text = item.inventoryName,
                                color = colorText,
                                maxLines = 2,
                                fontSize = DynamicTextFourteen
                            )
                        }
                    }
                )
            }
        )
        FooterDrawer()
    }
}

val listInventory = listOf(
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

val listSociedad = listOf(
    Sociedad(
        id = 0,
        sociedadName = "primero",
        listInventories = listInventory
    ),
    Sociedad(
        id = 1,
        sociedadName = "segundo",
        listInventories = listInventory
    ),
    Sociedad(
        id = 2,
        sociedadName = "tercero",
        listInventories = listInventory
    ),
    Sociedad(
        id = 3,
        sociedadName = "cuarto",
        listInventories = listInventory
    )
)

@Preview
@Composable
fun PreviewDarkContentDrawer() {
    ConsultaInventarioTheme(
        darkTheme = true
    ) {
        ContentDrawer(
            listItems = listSociedad,
            data = Data(
                userId = 1982,
                userName = "Jak Motero",
                listSociedades = listOf()
            ),
            onItemSelected = { _, _ -> },
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
            listItems = listSociedad,
            data = Data(
                userId = 1982,
                userName = "Jak Motero",
                listSociedades = listOf()
            ),
            onItemSelected = { _, _ -> },
            onClosedSession = {},
        )
    }
}
