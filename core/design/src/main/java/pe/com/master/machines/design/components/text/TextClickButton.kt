package pe.com.master.machines.design.components.text

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.design.theme.ContentInsetFive
import pe.com.master.machines.design.theme.ContentInsetFour
import pe.com.master.machines.design.theme.DynamicTextFourteen

@Composable
fun TextClickButton(
    textButton: String,
    modifier: Modifier = Modifier,
    textColorButton: Color = MaterialTheme.colorScheme.onPrimary,
    backgroundColorButton: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    val rounded = RoundedCornerShape(ContentInsetFour)
    TextButton(
        onClick = { onClick() },
        modifier = modifier,
        shape = rounded,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColorButton, contentColor = textColorButton
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = ContentInsetFive
        ),
        content = {
            CustomText(
                text = textButton,
                fontSize = DynamicTextFourteen,
                maxLines = 1,
                color = textColorButton,
            )
        }
    )
}

@Preview
@Composable
fun PreviewTextClickButtonLight() {
    ConsultaInventarioTheme(darkTheme = false) {
        TextClickButton(
            textButton = "Aceptar",
            modifier = Modifier.wrapContentWidth(),
            onClick = { }
        )
    }
}

@Preview
@Composable
fun PreviewTextClickButtonDark() {
    ConsultaInventarioTheme(darkTheme = true) {
        TextClickButton(
            textButton = "Aceptar",
            modifier = Modifier.wrapContentWidth(),
            onClick = { }
        )
    }
}
