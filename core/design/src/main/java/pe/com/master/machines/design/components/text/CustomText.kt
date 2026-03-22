package pe.com.master.machines.design.components.text

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.design.theme.DynamicTextTwentyFour
import pe.com.master.machines.design.theme.robotoRegular

@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    fontSize: TextUnit = DynamicTextTwentyFour,
    minLines: Int = 1,
    maxLines: Int = 3,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    fontStyle: FontStyle = FontStyle.Normal,
    isUnderlined: Boolean = false,
    fontFamily: FontFamily = FontFamily(robotoRegular),
    overflow: TextOverflow = TextOverflow.Ellipsis,
    style: TextStyle = LocalTextStyle.current,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign,
        lineHeight = fontSize * 1.3,
        maxLines = maxLines,
        minLines = minLines,
        fontWeight = fontWeight,
        overflow = overflow,
        style = style.merge(
            TextStyle(
                textDecoration = if (isUnderlined) TextDecoration.Underline else TextDecoration.None
            )
        ),
        fontFamily = fontFamily,
        onTextLayout = onTextLayout,
        fontStyle = fontStyle
    )
}

@Preview
@Composable
fun CustomTextLightPreview() {
    ConsultaInventarioTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            CustomText(
                text = "Texto en Modo Claro (On Surface)",
            )
        }
    }
}

@Preview
@Composable
fun CustomTextDarkPreview() {
    ConsultaInventarioTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            CustomText(
                text = "Texto en Modo Oscuro (On Surface)",
            )
        }
    }
}
