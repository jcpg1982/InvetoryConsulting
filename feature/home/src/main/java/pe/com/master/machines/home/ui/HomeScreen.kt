package pe.com.master.machines.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import pe.com.master.machines.design.components.dialogs.DialogAlert
import pe.com.master.machines.design.components.dialogs.LoadingDialog
import pe.com.master.machines.design.components.images.FullImageScreen
import pe.com.master.machines.design.components.row.ActivePdaRow
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.components.text.SearchText
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetSixteen
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.home.state.HomeState
import pe.com.master.machines.home.viewmodel.HomeViewmodel

@Composable
fun HomeScreen(
    sociedadId: Int,
    inventoryId: Int,
    sizeCodBarra: Int,
    viewModel: HomeViewmodel = hiltViewModel()
) {
    val context = LocalContext.current
    val scanner = remember { GmsBarcodeScanning.getClient(context) }

    val homeState by viewModel.homeState.collectAsStateWithLifecycle()

    var imageUrl by rememberSaveable { mutableStateOf("") }
    var searchText by rememberSaveable { mutableStateOf("") }
    var messageError by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = ContentInsetSixteen),
        content = {

            Spacer(modifier = Modifier.height(ContentInsetEight))

            SearchText(
                hintSearch = "Ingresar código de barra",
                value = searchText,
                onValueChange = { newValue ->
                    if (newValue != searchText) {
                        searchText = newValue
                        viewModel.resetHomeState()
                    }
                },
                maxCharacter = 100,
                onSearch = { query ->
                    if (query.isNotBlank()) {
                        val finalQuery =
                            if (sizeCodBarra > 0) query.padStart(sizeCodBarra, '0') else query
                        searchText = finalQuery
                        viewModel.getSearchActivePda(sociedadId, inventoryId, finalQuery)
                    }
                },
                onScanClick = {
                    scanner.startScan()
                        .addOnSuccessListener { barcode ->
                            val rawValue: String? = barcode.rawValue
                            rawValue?.let {
                                val finalQuery =
                                    if (sizeCodBarra > 0) it.padStart(sizeCodBarra, '0') else it
                                searchText = finalQuery
                                viewModel.getSearchActivePda(sociedadId, inventoryId, finalQuery)
                            }
                        }
                        .addOnFailureListener {
                            messageError = "Error al escanear: ${it.message}"
                        }
                }
            )

            when (val state = homeState) {
                HomeState.Loading -> LoadingDialog()
                is HomeState.Error -> {
                    DialogAlert(
                        title = "Consulta de Activo",
                        message = state.message,
                        onPositiveCallback = {
                            searchText = ""
                            viewModel.resetHomeState()
                        },
                        onDismissDialog = {
                            searchText = ""
                            viewModel.resetHomeState()
                        }
                    )
                }

                is HomeState.SuccessSearch -> {
                    val active = state.data
                    val listChildren = state.listChildren
                    val fatherActivePda = state.father
                    Spacer(modifier = Modifier.height(ContentInsetSixteen))
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = ContentInsetSixteen)
                    ) {
                        item {
                            CustomText(
                                text = "Detalles del Activo",
                                fontSize = DynamicTextSixteen,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = ContentInsetEight)
                            )
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.outlineVariant,
                                modifier = Modifier.padding(bottom = ContentInsetEight)
                            )
                        }

                        item {
                            ActivePdaRow(
                                item = active,
                                onClickImage = { url ->
                                    imageUrl = url
                                },
                                isChildren = false
                            )
                        }

                        if (fatherActivePda.idActivosPda > 0) {
                            item {
                                CustomText(
                                    text = "Detalles del Activo Padre",
                                    fontSize = DynamicTextSixteen,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(vertical = ContentInsetEight)
                                )
                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.outlineVariant,
                                    modifier = Modifier.padding(bottom = ContentInsetEight)
                                )
                            }

                            item {
                                ActivePdaRow(
                                    item = fatherActivePda,
                                    onClickImage = { url ->
                                        imageUrl = url
                                    },
                                    isChildren = false
                                )
                            }
                        }

                        if (listChildren.isNotEmpty()) {
                            item {

                                Spacer(modifier = Modifier.height(ContentInsetSixteen))

                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.outlineVariant,
                                    modifier = Modifier.padding(bottom = ContentInsetEight)
                                )
                                CustomText(
                                    text = "Listado de activos asociados",
                                    fontSize = DynamicTextSixteen,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(vertical = ContentInsetEight)
                                )
                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.outlineVariant,
                                    modifier = Modifier.padding(bottom = ContentInsetEight)
                                )
                            }

                            listChildren.forEach { child ->
                                item {
                                    ActivePdaRow(
                                        item = child,
                                        onClickImage = { url ->
                                            imageUrl = url
                                        },
                                        isChildren = true
                                    )
                                }

                                item {
                                    Spacer(
                                        modifier = Modifier.height(ContentInsetEight)
                                    )
                                }
                            }
                        }
                    }
                }

                else -> {}
            }
        }
    )

    if (imageUrl.isNotBlank()) {
        FullImageScreen(
            imageUrl = imageUrl,
            onNavigateToBack = {
                imageUrl = ""
            }
        )
    }

    if (messageError.isNotBlank()) {
        DialogAlert(
            title = "Error al escanear el código de barras",
            message = messageError,
            onPositiveCallback = {
                searchText = ""
                messageError = ""
            },
            onDismissDialog = {
                searchText = ""
                messageError = ""
            }
        )
    }
}
