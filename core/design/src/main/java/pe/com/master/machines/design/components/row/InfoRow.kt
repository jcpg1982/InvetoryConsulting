package pe.com.master.machines.design.components.row

import androidx.compose.foundation.Canvas
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
        verticalAlignment = Alignment.CenterVertically
    ) {
        // El label ocupa solo lo que necesita, pero permitimos que crezca hasta la mitad si es necesario
        CustomText(
            text = label,
            modifier = Modifier.weight(1f, fill = false),
            fontSize = DynamicTextFourteen,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1
        )

        val dotColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
        
        // El Canvas tiene weight(1f) y fill = true (por defecto), 
        // lo que hace que expanda y empuje al 'value' hacia la derecha.
        Canvas(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .height(1.dp)
        ) {
            drawLine(
                color = dotColor,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(2.dp.toPx(), 4.dp.toPx()), 0f),
                strokeWidth = 2.dp.toPx()
            )
        }

        // El value ocupa solo lo que necesita y se mantiene al final del Row
        CustomText(
            text = value,
            fontSize = DynamicTextFourteen,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.End,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoRow() {
    ConsultaInventarioTheme {
        InfoRow(label = "Marca", value = "Caterpillar")
    }
}
