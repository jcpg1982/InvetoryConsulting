package pe.com.master.machines.design.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SearchDropdownText(
    searchText: String = "",
    suggestions: List<T>,
    suggestionToString: (T) -> String,
    hintSearch: String,
    primaryColor: Color,
    colorText: Color,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Search,
    maxCharacter: Int = 100,
    onSuggestionSelected: (T) -> Unit,
    onMessageSearch: (String) -> Unit
) {
    var filterName by rememberSaveable { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(searchText) {
        if (searchText.isNotEmpty()) {
            filterName = searchText
        }
    }

    val showDropdown = expanded && filterName.isNotEmpty() && suggestions.isNotEmpty()

    ExposedDropdownMenuBox(
        expanded = showDropdown,
        onExpandedChange = { expanded = it },
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = filterName,
            onValueChange = {
                if (it.length <= maxCharacter) {
                    filterName = it
                    onMessageSearch(filterName.lowercase())
                    expanded = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
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
                    contentDescription = "search icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            trailingIcon = {
                if (filterName.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "clear data",
                        modifier = Modifier.clickable {
                            filterName = ""
                            onMessageSearch(filterName)
                            expanded = false
                        },
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    expanded = false
                }
            ),
            minLines = 1,
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedBorderColor = primaryColor,
                focusedBorderColor = primaryColor
            )
        )

        ExposedDropdownMenu(
            expanded = showDropdown,
            onDismissRequest = { expanded = false }
        ) {
            suggestions.forEach { suggestion ->
                DropdownMenuItem(
                    text = {
                        CustomText(
                            text = suggestionToString(suggestion),
                            fontSize = DynamicTextFourteen,
                            color = colorText
                        )
                    },
                    onClick = {
                        val selectedString = suggestionToString(suggestion)
                        filterName = selectedString
                        onSuggestionSelected(suggestion)
                        expanded = false
                        keyboardController?.hide()
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDarkSearchDropdownText() {
    ConsultaInventarioTheme(darkTheme = true) {
        SearchDropdownText(
            suggestions = listOf("Manzana", "Banana", "Cereza", "Damasco"),
            suggestionToString = { it },
            hintSearch = "Buscar fruta",
            primaryColor = Color.Blue,
            colorText = Color.Black,
            onSuggestionSelected = {},
            onMessageSearch = {}
        )
    }
}

@Preview
@Composable
fun PreviewLightSearchDropdownText() {
    ConsultaInventarioTheme(darkTheme = false) {
        SearchDropdownText(
            suggestions = listOf("Manzana", "Banana", "Cereza", "Damasco"),
            suggestionToString = { it },
            hintSearch = "Buscar fruta",
            primaryColor = Color.Blue,
            colorText = Color.Black,
            onSuggestionSelected = {},
            onMessageSearch = {}
        )
    }
}
