package pe.com.master.machines.home.ui

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import pe.com.master.machines.design.components.dialogs.DialogAlert
import pe.com.master.machines.design.components.dialogs.LoadingDialog
import pe.com.master.machines.design.components.images.CustomImage
import pe.com.master.machines.design.components.row.InfoRow
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
    onNavigateToFullImage: (String) -> Unit,
    viewModel: HomeViewmodel = hiltViewModel()
) {

    var messageError by rememberSaveable { mutableStateOf("") }
    var messageLoading by rememberSaveable { mutableStateOf("") }
    var activePdaState by remember { mutableStateOf<ActivePda?>(null) }

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
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = ContentInsetSixteen)
    ) {

        Spacer(modifier = Modifier.height(ContentInsetEight))

        SearchText(
            hintSearch = "Ingresar código de barra",
            maxCharacter = 100,
            onMessageSearch = { query ->
                if (query.isNotBlank()) {
                    viewModel.getSearchActivePda(sociedadId, inventoryId, query)
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

                item { InfoRow(label = "Código de Barra", value = active.codBarraNew) }
                item { InfoRow(label = "Descripción", value = active.desActivoNew) }
                item { InfoRow(label = "Marca", value = active.desMarcaNew) }
                item { InfoRow(label = "Modelo", value = active.desModeloNew) }
                item { InfoRow(label = "Serie", value = active.nroSerieNew) }
                item { InfoRow(label = "Placa", value = active.nroPlacaNew) }
                item { InfoRow(label = "Chasis", value = active.nroChasis) }
                item { InfoRow(label = "Motor", value = active.nroMotorNew) }
                item { InfoRow(label = "Estado", value = active.estado) }
                item { InfoRow(label = "Centro", value = active.desCentroNew) }
                item { InfoRow(label = "Capacidad", value = active.desCapacidadNew) }
                item { InfoRow(label = "Color", value = active.desColorNew) }
                item { InfoRow(label = "Potencia", value = active.desPotenciaNew) }
                item { InfoRow(label = "Proceso", value = active.desProcesoNew) }
                item { InfoRow(label = "Tipo Activo", value = active.desTipoActivoNew) }
                item { InfoRow(label = "Tag", value = active.tag) }
                item { InfoRow(label = "Horómetro", value = active.horometro.toString()) }
                item { InfoRow(label = "Fecha Modif.", value = active.fechaUltModificacion) }
                item { InfoRow(label = "Observación", value = active.desObservacionNew) }
                if (active.fotoPathUrl.isNotBlank()) {
                    item {
                        CustomImage(
                            model = active.fotoPathUrl,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = ContentInsetEight)
                                .clickable { onNavigateToFullImage(active.fotoPathUrl) },
                            contentScale = ContentScale.Crop,
                            viewShimmer = true
                        )
                    }
                }
            }
        }
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
