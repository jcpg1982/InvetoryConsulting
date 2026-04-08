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
import androidx.compose.runtime.LaunchedEffect
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
import pe.com.master.machines.model.model.ActivePda

@Composable
fun HomeScreen(
    sociedadId: Int,
    inventoryId: Int,
    sizeCodBarra: Int = 13,
    viewModel: HomeViewmodel = hiltViewModel()
) {
    val context = LocalContext.current
    val scanner = remember { GmsBarcodeScanning.getClient(context) }

    var imageUrl by rememberSaveable { mutableStateOf("") }
    var searchText by rememberSaveable { mutableStateOf("") }
    var messageError by rememberSaveable { mutableStateOf("") }
    var messageLoading by rememberSaveable { mutableStateOf("") }
    var activePda by remember { mutableStateOf<ActivePda?>(null) }
    var listChildren by remember { mutableStateOf<List<ActivePda>?>(null) }
    var fatherActivePda by remember { mutableStateOf<ActivePda?>(null) }

    LaunchedEffect(Unit) {
        viewModel.homeState.collect { homeState ->
            when (homeState) {
                is HomeState.Error -> {
                    activePda = null
                    messageLoading = ""
                    messageError = homeState.message
                }

                HomeState.Loading -> {
                    activePda = null
                    messageError = ""
                    messageLoading = "Cargando..."
                }

                is HomeState.SuccessSearch -> {
                    messageLoading = ""
                    messageError = ""
                    activePda = homeState.data
                    listChildren = homeState.listChildren
                    fatherActivePda = homeState.father
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = ContentInsetSixteen),
        content = {

            Spacer(modifier = Modifier.height(ContentInsetEight))

            SearchText(
                hintSearch = "Ingresar código de barra",
                value = searchText,
                onValueChange = { searchText = it },
                maxCharacter = 100,
                onSearch = { query ->
                    if (query.isNotBlank()) {
                        viewModel.getSearchActivePda(sociedadId, inventoryId, query)
                    }
                },
                onScanClick = {
                    scanner.startScan()
                        .addOnSuccessListener { barcode ->
                            val rawValue: String? = barcode.rawValue
                            rawValue?.let {
                                searchText = it
                                viewModel.getSearchActivePda(sociedadId, inventoryId, it)
                            }
                        }
                        .addOnFailureListener {
                            messageError = "Error al escanear: ${it.message}"
                        }
                }
            )

            activePda?.let { active ->
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
                            }
                        )
                    }

                    fatherActivePda?.let { father ->
                        if (father.idActivosPda > 0) {
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
                                    item = father,
                                    onClickImage = { url ->
                                        imageUrl = url
                                    }
                                )
                            }
                        }
                    }

                    if (!listChildren.isNullOrEmpty()) {
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

                        listChildren?.forEachIndexed { index, child ->
                            item {
                                ActivePdaRow(
                                    item = child,
                                    onClickImage = { url ->
                                        imageUrl = url
                                    }
                                )
                            }
                            if (index < listChildren!!.size - 1) {
                                item {
                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        color = MaterialTheme.colorScheme.outlineVariant,
                                        modifier = Modifier.padding(bottom = ContentInsetEight)
                                    )
                                }
                            }
                        }
                    }
                }
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
            title = "Consulta de Activo",
            message = messageError,
            onPositiveCallback = {
                messageError = ""
            },
            onDismissDialog = {
                messageError = ""
            }
        )
    }

    if (messageLoading.isNotBlank()) {
        LoadingDialog()
    }
}
