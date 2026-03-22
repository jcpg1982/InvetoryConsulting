package pe.com.master.machines.design.components.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ContentInsetFour
import pe.com.master.machines.design.theme.DynamicTextFourteen

@Composable
fun ItemDialogRow(text: String, color: Color, onItemsCallback: () -> Unit) {
    CustomText(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = ContentInsetFour)
            .clickable { onItemsCallback.invoke() },
        color = color,
        fontSize = DynamicTextFourteen,
        maxLines = 1,
        minLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Preview
@Composable
fun PreviewItemDialogRow(modifier: Modifier = Modifier) {
    ItemDialogRow(
        text = "texto ingresado",
        color = MaterialTheme.colorScheme.onBackground
    ) { }
}