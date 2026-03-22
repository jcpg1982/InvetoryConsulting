package pe.com.master.machines.design.components.dialogs.typeDialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import pe.com.master.machines.design.components.list.LazyColumnCustom
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.components.text.CustomTextInput
import pe.com.master.machines.design.components.text.TextClickButton
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.ContentInsetFour
import pe.com.master.machines.design.theme.ContentInsetSixteen
import pe.com.master.machines.design.utils.Constants.Regex.ONLY_LETTERS

@Composable
inline fun <reified T> ItemOthers(
    title: String,
    listItems: List<T>,
    primaryColor: Color,
    hintOthers: String,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    crossinline onPositiveCallback: () -> Unit,
    crossinline onItemsCallback: (Int, T) -> Unit,
    crossinline onInputCallback: (String) -> Unit
) {
    var others by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(ContentInsetSixteen)
    ) {
        CustomText(text = title)
        LazyColumnCustom(
            listItems = listItems,
            primaryColor = primaryColor,
            onItemsCallback = { pos, data -> onItemsCallback(pos, data) }
        )
        CustomTextInput(modifier = Modifier
            .fillMaxWidth()
            .padding(top = ContentInsetFour),
            hintText = hintOthers,
            value = others,
            maxCharacter = 10,
            isEnabled = true,
            isReadOnly = false,
            keyboardType = KeyboardType.Text,
            maxLines = 1,
            regex = ONLY_LETTERS,
            onTextValueChange = {
                others = it
                onInputCallback(others)
            },
            onClickTextView = {})
        if (others.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = ContentInsetSixteen),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextClickButton(
                    modifier = Modifier.wrapContentWidth(),
                    textButton = textPositiveButton,
                    textColorButton = textColorPositiveButton,
                    backgroundColorButton = backgroundColorPositiveButton
                ) { onPositiveCallback() }
            }
        }
    }
}