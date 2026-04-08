package pe.com.master.machines.design.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.design.theme.DynamicTextFourteen
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.theme.robotoRegular

@Composable
fun SearchText(
    hintSearch: String,
    value: String,
    modifier: Modifier = Modifier,
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    colorText: Color = MaterialTheme.colorScheme.onSurface,
    imeAction: ImeAction = ImeAction.Search,
    maxCharacter: Int = 100,
    onSearch: (String) -> Unit,
    onScanClick: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    var textFieldValueState by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        )
    }

    LaunchedEffect(value) {
        if (textFieldValueState.text != value) {
            textFieldValueState = TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        }
    }

    OutlinedTextField(
        value = textFieldValueState,
        onValueChange = {
            if (it.text.length <= maxCharacter) {
                textFieldValueState = it
                onValueChange(it.text)
            }
        },
        modifier = modifier.fillMaxWidth(),
        textStyle = TextStyle(
            color = colorText,
            fontSize = DynamicTextFourteen,
            fontFamily = FontFamily(robotoRegular)
        ),
        label = {
            CustomText(
                text = hintSearch,
                fontSize = DynamicTextSixteen,
                color = colorText.copy(alpha = 0.5f)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = primaryColor
            )
        },
        trailingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                if (value.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Clear text",
                        modifier = Modifier.clickable {
                            onValueChange("")
                            onSearch("")
                        },
                        tint = primaryColor
                    )
                }
                Icon(
                    imageVector = Icons.Filled.QrCodeScanner,
                    contentDescription = "Scan barcode",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { onScanClick() },
                    tint = primaryColor
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(textFieldValueState.text.trim())
                keyboardController?.hide()
            }
        ),
        minLines = 1,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedBorderColor = primaryColor.copy(alpha = 0.5f),
            focusedBorderColor = primaryColor,
            cursorColor = primaryColor
        )
    )
}

@Preview(showBackground = true)
@Composable
fun GetPreviewSearchTextLight() {
    ConsultaInventarioTheme(darkTheme = false) {
        SearchText(
            hintSearch = "Buscar",
            value = "",
            onSearch = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GetPreviewSearchTextDark() {
    ConsultaInventarioTheme(darkTheme = true) {
        SearchText(
            hintSearch = "Buscar",
            value = "",
            onSearch = { }
        )
    }
}
