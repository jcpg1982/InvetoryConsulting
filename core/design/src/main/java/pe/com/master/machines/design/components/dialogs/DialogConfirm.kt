package pe.com.master.machines.design.components.dialogs

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.model.enums.DialogType

@Composable
fun DialogConfirm(
    title: String,
    message: String,
    isCancelable: Boolean,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    onPositiveCallback: () -> Unit,
    textNegativeButton: String,
    textColorNegativeButton: Color,
    backgroundColorNegativeButton: Color,
    onNegativeCallback: () -> Unit,
    onDismissDialog: () -> Unit = {}
) {
    CustomDefaultDialog<Any>(
        title = title,
        message = message,
        dialogType = DialogType.CONFIRM,
        isCancelable = isCancelable,
        textPositiveButton = textPositiveButton,
        textColorPositiveButton = textColorPositiveButton,
        backgroundColorPositiveButton = backgroundColorPositiveButton,
        onPositiveCallback = onPositiveCallback,
        textNegativeButton = textNegativeButton,
        textColorNegativeButton = textColorNegativeButton,
        backgroundColorNegativeButton = backgroundColorNegativeButton,
        onNegativeCallback = onNegativeCallback,
        onDismissDialog = onDismissDialog
    )
}

@Preview
@Composable
fun PreviewDialogConfirm() {
    DialogConfirm(
        title = "titulo",
        message = "mensaje",
        isCancelable = false,
        textPositiveButton = "aceptar",
        textColorPositiveButton = MaterialTheme.colorScheme.onPrimary,
        backgroundColorPositiveButton = ColorWhite,
        onPositiveCallback = { },
        textNegativeButton = "cancelar",
        textColorNegativeButton = MaterialTheme.colorScheme.onSecondary,
        backgroundColorNegativeButton = ColorWhite,
        onNegativeCallback = { }) {

    }
}