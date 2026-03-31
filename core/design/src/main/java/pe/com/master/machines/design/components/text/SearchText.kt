package pe.com.master.machines.design.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.design.theme.DynamicTextFourteen
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.theme.robotoRegular

@Composable
fun SearchText(
    hintSearch: String,
    modifier: Modifier = Modifier,
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    colorText: Color = MaterialTheme.colorScheme.onSurface,
    imeAction: ImeAction = ImeAction.Search,
    maxCharacter: Int = 50,
    onMessageSearch: (String) -> Unit
) {

    var filterName by rememberSaveable { mutableStateOf("58222210020255") }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = filterName,
        onValueChange = {
            if (it.length <= maxCharacter) {
                filterName = it
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
            if (filterName.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Clear text",
                    modifier = Modifier.clickable {
                        filterName = ""
                        onMessageSearch("")
                    },
                    tint = primaryColor
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onMessageSearch(filterName.trim().lowercase())
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
            onMessageSearch = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GetPreviewSearchTextDark() {
    ConsultaInventarioTheme(darkTheme = true) {
        SearchText(
            hintSearch = "Buscar",
            onMessageSearch = { }
        )
    }
}
