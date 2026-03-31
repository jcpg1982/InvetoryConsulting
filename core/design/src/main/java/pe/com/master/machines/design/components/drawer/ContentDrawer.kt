package pe.com.master.machines.design.components.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.com.master.machines.design.components.text.CustomText
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
    if (listItems.isEmpty()) return

    // Estado para la Sociedad seleccionada
    var selectedSociedad by remember {
        mutableStateOf(listItems.first())
    }

    // El inventario seleccionado se reinicia cuando cambia la sociedad
    var selectedInventory by remember(selectedSociedad) {
        mutableStateOf(selectedSociedad.listInventories.firstOrNull())
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        HeaderDrawer(data = data)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            // Columna 1: Sociedades
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                items(listItems) { item ->
                    val isSelected = item.id == selectedSociedad.id
                    val backgroundColor = if (isSelected) 
                        MaterialTheme.colorScheme.primaryContainer 
                    else 
                        MaterialTheme.colorScheme.surface
                    
                    val textColor = if (isSelected) 
                        MaterialTheme.colorScheme.onPrimaryContainer 
                    else 
                        MaterialTheme.colorScheme.onSurface

                    CustomText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = backgroundColor)
                            .clickable { selectedSociedad = item }
                            .padding(ContentInsetEight),
                        text = item.sociedadName,
                        color = textColor,
                        maxLines = 3,
                        fontSize = DynamicTextFourteen
                    )
                }
            }

            // Columna 2: Inventarios (con un fondo sutil para contraste)
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f))
            ) {
                items(selectedSociedad.listInventories) { item ->
                    val isSelected = item.id == selectedInventory?.id
                    val backgroundColor = if (isSelected) 
                        MaterialTheme.colorScheme.secondaryContainer 
                    else 
                        MaterialTheme.colorScheme.surface.copy(alpha = 0f) // Transparente para ver el fondo de la columna
                    
                    val textColor = if (isSelected) 
                        MaterialTheme.colorScheme.onSecondaryContainer 
                    else 
                        MaterialTheme.colorScheme.onSurface

                    CustomText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = backgroundColor)
                            .clickable {
                                selectedInventory = item
                                onItemSelected(selectedSociedad.id, item.id)
                            }
                            .padding(ContentInsetEight),
                        text = item.inventoryName,
                        color = textColor,
                        maxLines = 2,
                        fontSize = DynamicTextFourteen
                    )
                }
            }
        }

        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)

        // Acción de Cerrar Sesión
        CustomText(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface)
                .clickable { onClosedSession() }
                .padding(ContentInsetEight),
            text = "Cerrar Sesión",
            color = MaterialTheme.colorScheme.error,
            fontSize = DynamicTextFourteen,
            maxLines = 1
        )

        FooterDrawer()
    }
}

// Previews con datos de prueba
private val mockInventories = listOf(
    Inventory(id = 0, inventoryName = "Almacén Central"),
    Inventory(id = 1, inventoryName = "Sede Norte"),
    Inventory(id = 2, inventoryName = "Stock Mantenimiento")
)

private val mockSociedades = listOf(
    Sociedad(id = 0, sociedadName = "Master Machines SAC", listInventories = mockInventories),
    Sociedad(id = 1, sociedadName = "Logística General", listInventories = mockInventories.take(1)),
    Sociedad(id = 2, sociedadName = "Servicios Industriales", listInventories = mockInventories.drop(1))
)

@Preview(showBackground = true)
@Composable
fun PreviewContentDrawerLight() {
    ConsultaInventarioTheme(darkTheme = false) {
        ContentDrawer(
            listItems = mockSociedades,
            data = Data(documentNumber = "70654321", userName = "Carlos Mejia", listSociedades = listOf()),
            onItemSelected = { _, _ -> },
            onClosedSession = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContentDrawerDark() {
    ConsultaInventarioTheme(darkTheme = true) {
        ContentDrawer(
            listItems = mockSociedades,
            data = Data(documentNumber = "70654321", userName = "Carlos Mejia", listSociedades = listOf()),
            onItemSelected = { _, _ -> },
            onClosedSession = {}
        )
    }
}
