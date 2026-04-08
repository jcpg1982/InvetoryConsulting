package pe.com.master.machines.design.components.row

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import pe.com.master.machines.design.components.images.CustomImage
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.utils.DateUtils.FORMAT_DD_MM_YYYY
import pe.com.master.machines.design.utils.DateUtils.formatDate
import pe.com.master.machines.model.model.ActivePda

@Composable
fun ActivePdaRow(
    item: ActivePda,
    onClickImage: (String) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        content = {
            InfoRow(label = "Cód Barra padre", value = item.codBarraPadreNew)
            InfoRow(label = "Cód Barra", value = item.codBarraNew)
            InfoRow(label = "Descripción", value = item.desActivoNew)
            InfoRow(label = "Marca", value = item.desMarcaNew)
            InfoRow(label = "Modelo", value = item.desModeloNew)
            InfoRow(label = "Serie", value = item.nroSerieNew)
            InfoRow(label = "Placa", value = item.nroPlacaNew)
            InfoRow(label = "Chasis", value = item.nroChasis)
            InfoRow(label = "Motor", value = item.nroMotorNew)
            InfoRow(label = "Centro", value = item.desCentroNew)
            InfoRow(label = "Capacidad", value = item.desCapacidadNew)
            InfoRow(label = "Color", value = item.desColorNew)
            InfoRow(label = "Potencia", value = item.desPotenciaNew)
            InfoRow(label = "Proceso", value = item.desProcesoNew)
            InfoRow(label = "Tipo Activo", value = item.desTipoActivoNew)
            InfoRow(
                label = "Operativo",
                value = if (item.operativo == 1) "Operativo" else "Inoperativo"
            )
            InfoRow(
                label = "Componente",
                value = item.componenteCompleto.toString()
            )
            InfoRow(label = "Tag", value = item.tag)
            InfoRow(label = "Horómetro", value = item.horometro.toString())
            InfoRow(
                label = "Fecha.",
                value = formatDate(item.fecha, FORMAT_DD_MM_YYYY)
            )
            InfoRow(
                label = "Fecha Modif.",
                value = formatDate(item.fechaUltModificacion, FORMAT_DD_MM_YYYY)
            )
            InfoRow(label = "Observación", value = item.desObservacionNew)
            if (item.fotoPathUrl.isNotBlank()) {
                val fileName =
                    if (item.fotoPathUrl.startsWith("/")) item.fotoPathUrl else "/${item.fotoPathUrl}"
                val photoUrl = "https://api.dmycm.com.pe/robocon-storage$fileName"
                CustomImage(
                    model = photoUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = ContentInsetEight)
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                        .clickable { onClickImage(photoUrl) },
                    contentScale = ContentScale.Inside,
                    viewShimmer = true
                )
            }
        }
    )

}