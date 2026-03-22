package pe.com.master.machines.design.components.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.DynamicTextFourteen
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.theme.DynamicTextTwelve
import pe.com.master.machines.design.theme.robotoRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> CustomMultiSelectDropdownInput(
    selectedItems: List<T>,
    items: List<T>,
    itemLabel: (T) -> String,
    itemId: (T) -> String,
    hintText: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    colorSupportingText: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    supportingText: String = "",
    messageError: String = "",
    trailingIcon: ImageVector? = null,
    colorTrailingIcon: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    itemLetter: ((T) -> String)? = null,
    itemColor: ((T) -> Color)? = null,
    onItemSelected: (T) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val isError = messageError.isNotBlank()
    val displayText =
        if (selectedItems.isEmpty()) "" else selectedItems.joinToString(", ") { itemLabel(it) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { if (isEnabled) expanded = !expanded },
        modifier = modifier.fillMaxWidth(),
    ) {
        OutlinedTextField(
            value = displayText,
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
            isError = isError,
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 300.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                        .verticalScroll(rememberScrollState())
                ) {
                    items.forEach { item ->
                        val isSelected = selectedItems.any { itemId(it) == itemId(item) }
                        DropdownMenuItem(
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Checkbox(
                                        checked = isSelected,
                                        onCheckedChange = null
                                    )

                                    if (itemLetter != null && itemColor != null) {
                                        /*TextDrawable(
                                            text = itemLetter(item),
                                            backgroundColor = itemColor(item),
                                            textColor = Color.White,
                                            shape = RoundedCornerShape(ContentInsetTwo),
                                            modifier = Modifier.size(ContentInsetEight.times(3)),
                                            textScale = 0.7f
                                        )*/
                                        Spacer(modifier = Modifier.width(ContentInsetEight))
                                    }

                                    CustomText(
                                        text = itemLabel(item),
                                        overflow = TextOverflow.Ellipsis,
                                        minLines = 1,
                                        maxLines = 1,
                                        fontSize = DynamicTextTwelve,
                                    )
                                }
                            },
                            onClick = {
                                onItemSelected(item)
                            }
                        )
                    }
                }

                if (selectedItems.isNotEmpty()) {
                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                    DropdownMenuItem(
                        text = {
                            CustomText(
                                text = "Calcular con bancos seleccionados",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = DynamicTextFourteen
                            )
                        },
                        onClick = {
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
