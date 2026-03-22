package pe.com.master.machines.design.components.text

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.theme.ContentInsetSixteen
import pe.com.master.machines.design.theme.ContentInsetFour
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.theme.DynamicTextTwelve
import pe.com.master.machines.design.utils.Constants.Regex.MIXTO
import pe.com.master.machines.design.utils.Constants.Regex.ONLY_LETTERS

@Composable
fun CustomTextInput(
    value: String,
    hintText: String,
    modifier: Modifier = Modifier,
    maxCharacter: Int = 150,
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colorSupportingText: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    supportingText: String = "",
    messageError: String = "",
    trailingIcon: ImageVector? = null,
    colorTrailingIcon: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    onTrailingIconClick: (() -> Unit)? = null,
    minLines: Int = 1,
    maxLines: Int = 5,
    regex: Regex = MIXTO,
    onTextValueChange: (String) -> Unit = {},
    onClickTextView: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isError = messageError.isNotBlank()
    var passwordVisible by remember { mutableStateOf(false) }
    val isPasswordType = keyboardType == KeyboardType.Password

    val finalVisualTransformation = if (isPasswordType) {
        if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    } else visualTransformation

    val finalTrailingIcon = if (isPasswordType && !isError) {
        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
    } else trailingIcon

    val finalOnTrailingIconClick = if (isPasswordType && !isError) {
        { passwordVisible = !passwordVisible }
    } else onTrailingIconClick

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxCharacter && it.matches(regex = regex)) {
                onTextValueChange(it)
            }
        },
        modifier = modifier,
        enabled = isEnabled,
        readOnly = isReadOnly,
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
            } else if (finalTrailingIcon != null) {
                if (finalOnTrailingIconClick != null) {
                    IconButton(onClick = finalOnTrailingIconClick) {
                        Icon(
                            imageVector = finalTrailingIcon,
                            contentDescription = null,
                            tint = colorTrailingIcon
                        )
                    }
                } else {
                    Icon(
                        imageVector = finalTrailingIcon,
                        contentDescription = null,
                        tint = colorTrailingIcon
                    )
                }
            }
        },
        isError = isError,
        visualTransformation = finalVisualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = maxLines == 1,
        minLines = minLines,
        maxLines = maxLines,
        interactionSource = interactionSource
    )

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                onClickTextView()
            }
        }
    }
}

@Composable
fun CustomTextInput(
    value: TextFieldValue,
    hintText: String,
    modifier: Modifier = Modifier,
    maxCharacter: Int = 150,
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colorSupportingText: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    supportingText: String = "",
    messageError: String = "",
    trailingIcon: ImageVector? = null,
    colorTrailingIcon: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    onTrailingIconClick: (() -> Unit)? = null,
    minLines: Int = 1,
    maxLines: Int = 5,
    regex: Regex = MIXTO,
    onTextValueChange: (String) -> Unit = {},
    onClickTextView: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isError = messageError.isNotBlank()
    var passwordVisible by remember { mutableStateOf(false) }
    val isPasswordType = keyboardType == KeyboardType.Password

    val finalVisualTransformation = if (isPasswordType) {
        if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    } else visualTransformation

    val finalTrailingIcon = if (isPasswordType && !isError) {
        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
    } else trailingIcon

    val finalOnTrailingIconClick = if (isPasswordType && !isError) {
        { passwordVisible = !passwordVisible }
    } else onTrailingIconClick

    OutlinedTextField(
        value = value,
        onValueChange = {
            val result = it.text
            if (result.length <= maxCharacter && result.matches(regex = regex)) {
                onTextValueChange(result)
            }
        },
        modifier = modifier,
        enabled = isEnabled,
        readOnly = isReadOnly,
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
            } else if (finalTrailingIcon != null) {
                if (finalOnTrailingIconClick != null) {
                    IconButton(onClick = finalOnTrailingIconClick) {
                        Icon(
                            imageVector = finalTrailingIcon,
                            contentDescription = null,
                            tint = colorTrailingIcon
                        )
                    }
                } else {
                    Icon(
                        imageVector = finalTrailingIcon,
                        contentDescription = null,
                        tint = colorTrailingIcon
                    )
                }
            }
        },
        isError = isError,
        visualTransformation = finalVisualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = maxLines == 1,
        minLines = minLines,
        maxLines = maxLines,
        interactionSource = interactionSource
    )

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                onClickTextView()
            }
        }
    }
}

@Preview
@Composable
fun GetPreviewCustomTextInput() {
    CustomTextInput(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = ContentInsetSixteen, end = ContentInsetSixteen, top = ContentInsetFour
            ),
        hintText = "ingrese un dato",
        value = "",
        maxCharacter = 50,
        isEnabled = true,
        isReadOnly = false,
        keyboardType = KeyboardType.Text,
        messageError = "",
        maxLines = 1,
        regex = ONLY_LETTERS,
        onTextValueChange = {},
        onClickTextView = {})
}
