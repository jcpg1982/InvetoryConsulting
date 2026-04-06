package pe.com.master.machines.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import pe.com.master.machines.design.components.dialogs.DialogAlert
import pe.com.master.machines.design.components.dialogs.LoadingDialog
import pe.com.master.machines.design.components.images.CustomImage
import pe.com.master.machines.design.components.images.FullImageScreen
import pe.com.master.machines.design.components.row.InfoRow
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.components.text.SearchText
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetSixteen
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.utils.DateUtils.FORMAT_DD_MM_YYYY
import pe.com.master.machines.design.utils.DateUtils.formatDate
import pe.com.master.machines.home.state.HomeState
import pe.com.master.machines.home.viewmodel.HomeViewmodel
import pe.com.master.machines.model.model.ActivePda

@Composable
fun HomeScreen(
    sociedadId: Int,
    inventoryId: Int,
    viewModel: HomeViewmodel = hiltViewModel()
) {
    val context = LocalContext.current
    val scanner = remember { GmsBarcodeScanning.getClient(context) }

    var imageUrl by rememberSaveable { mutableStateOf("") }
    var searchText by rememberSaveable { mutableStateOf("") }
    var messageError by rememberSaveable { mutableStateOf("") }
    var messageLoading by rememberSaveable { mutableStateOf("") }
    var activePdaState by remember { mutableStateOf<ActivePda?>(null) }
    var listChildren by remember { mutableStateOf<List<ActivePda>?>(null) }

    LaunchedEffect(Unit) {
        viewModel.homeState.collect { homeState ->
            when (homeState) {
                is HomeState.Error -> {
                    activePdaState = null
                    messageLoading = ""
                    messageError = homeState.message
                }

                HomeState.Loading -> {
                    activePdaState = null
                    messageError = ""
                    messageLoading = "Cargando..."
                }

                is HomeState.SuccessSearch -> {
                    messageLoading = ""
                    messageError = ""
                    activePdaState = homeState.data
                    listChildren = homeState.listChildren
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
                onMessageSearch = { query ->
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

            activePdaState?.let { active ->
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

                    item { InfoRow(label = "Cód Barra padre", value = active.codBarraPadreNew) }
                    item { InfoRow(label = "Cód Barra", value = active.codBarraNew) }
                    item { InfoRow(label = "Descripción", value = active.desActivoNew) }
                    item { InfoRow(label = "Marca", value = active.desMarcaNew) }
                    item { InfoRow(label = "Modelo", value = active.desModeloNew) }
                    item { InfoRow(label = "Serie", value = active.nroSerieNew) }
                    item { InfoRow(label = "Placa", value = active.nroPlacaNew) }
                    item { InfoRow(label = "Chasis", value = active.nroChasis) }
                    item { InfoRow(label = "Motor", value = active.nroMotorNew) }
                    item { InfoRow(label = "Centro", value = active.desCentroNew) }
                    item { InfoRow(label = "Capacidad", value = active.desCapacidadNew) }
                    item { InfoRow(label = "Color", value = active.desColorNew) }
                    item { InfoRow(label = "Potencia", value = active.desPotenciaNew) }
                    item { InfoRow(label = "Proceso", value = active.desProcesoNew) }
                    item { InfoRow(label = "Tipo Activo", value = active.desTipoActivoNew) }
                    item {
                        InfoRow(
                            label = "Operativo",
                            value = if (active.operativo == 1) "Operativo" else "Inoperativo"
                        )
                    }
                    item {
                        InfoRow(
                            label = "Componente",
                            value = active.componenteCompleto.toString()
                        )
                    }
                    item { InfoRow(label = "Tag", value = active.tag) }
                    item { InfoRow(label = "Horómetro", value = active.horometro.toString()) }
                    item {
                        InfoRow(
                            label = "Fecha.",
                            value = formatDate(active.fecha, FORMAT_DD_MM_YYYY)
                        )
                    }
                    item {
                        InfoRow(
                            label = "Fecha Modif.",
                            value = formatDate(active.fechaUltModificacion, FORMAT_DD_MM_YYYY)
                        )
                    }
                    item { InfoRow(label = "Observación", value = active.desObservacionNew) }
                    if (active.fotoPathUrl.isNotBlank()) {
                        val fileName =
                            if (active.fotoPathUrl.startsWith("/")) active.fotoPathUrl else "/${active.fotoPathUrl}"
                        val photoUrl = "https://api.dmycm.com.pe/robocon-storage$fileName"
                        item {
                            CustomImage(
                                model = photoUrl,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = ContentInsetEight)
                                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                                    .clickable { imageUrl = photoUrl },
                                contentScale = ContentScale.Inside,
                                viewShimmer = true
                            )
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
                            item { InfoRow(label = "Cód Barra", value = child.codBarraNew) }
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
