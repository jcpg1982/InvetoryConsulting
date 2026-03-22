package pe.com.master.machines.design.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import pe.com.master.machines.design.theme.DynamicTextFourteen
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.theme.DynamicTextTwelve
import pe.com.master.machines.design.theme.robotoRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> CustomDropdownInput(
    value: T,
    items: List<T>,
    itemLabel: (T) -> String,
    hintText: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    colorSupportingText: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    supportingText: String = "",
    messageError: String = "",
    trailingIcon: ImageVector? = null,
    colorTrailingIcon: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    onItemSelected: (T) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val isError = messageError.isNotBlank()
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { if (isEnabled) expanded = !expanded },
        modifier = modifier
            .fillMaxWidth(),
    ) {
        OutlinedTextField(
            value = value.let(itemLabel),
            onValueChange = { },
            textStyle = TextStyle(
                fontSize = DynamicTextFourteen,
                fontFamily = FontFamily(robotoRegular)
            ),
            modifier = Modifier
                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                    enabled = isEnabled
                )
                .fillMaxWidth(),
            enabled = isEnabled,
            readOnly = true,
            label = {
                CustomText(
                    text = hintText,
                    overflow = TextOverflow.Ellipsis,
                    minLines = 1,
                    maxLines = 1,
                    fontSize = DynamicTextSixteen,
                )
            },
            supportingText = {
                val textToShow = if (isError) messageError else supportingText
                if (textToShow.isNotBlank()) {
                    CustomText(
                        modifier = Modifier.fillMaxWidth(),
                        text = textToShow,
                        color = if (isError) MaterialTheme.colorScheme.error else colorSupportingText,
                        fontSize = DynamicTextTwelve
                    )
                }
            },
            trailingIcon = {
                if (isError) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = "error",
                        tint = MaterialTheme.colorScheme.error
                    )
                } else if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        tint = colorTrailingIcon
                    )
                }
            },
            isError = messageError.isNotBlank(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        CustomText(
                            text = itemLabel(item),
                            overflow = TextOverflow.Ellipsis,
                            minLines = 1,
                            maxLines = 1,
                            fontSize = DynamicTextTwelve,
                        )
                    },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

/*@Preview
@Composable
fun PreviewCustomDropdownInput() {
    CustomDropdownInput(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = ContentInsetSixteen, end = ContentInsetSixteen, top = ContentInsetFour
            ),
        hintText = "ingrese un dato",
        items = getListDays,
        itemLabel = { it.label },
        value = Option(id = 1, label = ""),
        isEnabled = true,
        messageError = "",
        onItemSelected = { }
    )
}*/
