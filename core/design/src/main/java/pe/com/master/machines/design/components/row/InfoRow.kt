package pe.com.master.machines.design.components.row

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.DynamicTextFourteen

@Composable
fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = ContentInsetEight),
        verticalAlignment = Alignment.Top
    ) {
        CustomText(
            text = label,
            modifier = Modifier.weight(4f, fill = false),
            fontSize = DynamicTextFourteen,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2
        )

        val dotColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
        
        Box(
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = 8.dp)
                .height(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier.fillMaxWidth().height(1.dp)
            ) {
                drawLine(
                    color = dotColor,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(2.dp.toPx(), 4.dp.toPx()), 0f),
                    strokeWidth = 2.dp.toPx()
                )
            }
        }

        CustomText(
            text = value,
            modifier = Modifier.weight(5f, fill = false),
            fontSize = DynamicTextFourteen,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.End,
            maxLines = 3
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoRowLong() {
    ConsultaInventarioTheme {
        InfoRow(
            label = "Descripción del Activo Fijo", 
            value = "Caterpillar 320 GC con motor C4.4 y sistema hidráulico avanzado de alto rendimiento"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoRowShort() {
    ConsultaInventarioTheme {
        InfoRow(label = "Marca", value = "Caterpillar")
    }
}
