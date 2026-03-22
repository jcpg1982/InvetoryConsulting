package pe.com.master.machines.design.components.bottoms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetOne
import pe.com.master.machines.design.theme.ContentInsetSixteen
import pe.com.master.machines.design.theme.DynamicTextTwelve

@Composable
fun CustomTextButton(
    textButton: String,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    fontSize: TextUnit = DynamicTextTwelve,
    modifier: Modifier = Modifier,
    enabledButton: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    borderColor: Color = Color.Transparent,
    rounded: Dp = ContentInsetSixteen,
    onClickButton: () -> Unit
) {
    Button(
        onClick = { onClickButton() },
        modifier = modifier,
        enabled = enabledButton,
        shape = RoundedCornerShape(rounded),
        border = if (borderColor != Color.Transparent) BorderStroke(
            ContentInsetOne,
            borderColor
        ) else null,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        )
    ) {
        CustomText(
            modifier = Modifier.padding(vertical = ContentInsetEight),
            text = textButton,
            maxLines = 1,
            fontSize = fontSize,
            color = if (enabledButton) textColor else MaterialTheme.colorScheme.onSurface.copy(
                alpha = 0.38f
            )
        )
    }
}

@Preview
@Composable
fun PreviewCustomTextButtonLight() {
    ConsultaInventarioTheme(darkTheme = false) {
        Surface {
            Column(modifier = Modifier.padding(ContentInsetSixteen)) {
                CustomTextButton(
                    textButton = "Continuar",
                    modifier = Modifier.wrapContentWidth(),
                    onClickButton = {},
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomTextButtonDark() {
    ConsultaInventarioTheme(darkTheme = true) {
        Surface {
            Column(modifier = Modifier.padding(ContentInsetSixteen)) {
                CustomTextButton(
                    textButton = "Continuar",
                    modifier = Modifier.wrapContentWidth(),
                    onClickButton = {},
                )
            }
        }
    }
}

