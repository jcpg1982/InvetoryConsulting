package pe.com.master.machines.design.components.row

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.theme.ConsultaInventarioTheme

@Composable
fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    if (value.isBlank()) return
    if (value == "0") return
    if (value == "0.0") return

    BoxWithConstraints(
        modifier.fillMaxWidth(),
        content = {
            val maxLabelWidth = maxWidth * 0.4f
            val maxValueWidth = maxWidth * 0.5f
            ContentInfoRow(
                label = label,
                value = value,
                maxLabelWidth = maxLabelWidth,
                maxValueWidth = maxValueWidth
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoRowMixed() {
    ConsultaInventarioTheme {
        Column {
            InfoRow(label = "Marca", value = "CAT")
            InfoRow(
                label = "Descripción muy larga que debería ocupar dos líneas",
                value = "Este es un valor también bastante largo para probar el límite de tres líneas en la interfaz"
            )
            InfoRow(label = "Estado", value = "Operativo")
        }
    }
}
