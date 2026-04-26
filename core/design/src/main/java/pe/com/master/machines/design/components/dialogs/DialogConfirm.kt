package pe.com.master.machines.design.components.dialogs

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.model.enums.DialogType

@Composable
fun DialogConfirm(
    title: String,
    message: String,
    isCancelable: Boolean = true,
    textPositiveButton: String = "Aceptar",
    textColorPositiveButton: Color = MaterialTheme.colorScheme.onPrimary,
    backgroundColorPositiveButton: Color = MaterialTheme.colorScheme.primary,
    onPositiveCallback: () -> Unit,
    textNegativeButton: String = "Cancelar",
    textColorNegativeButton: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    backgroundColorNegativeButton: Color = MaterialTheme.colorScheme.secondaryContainer,
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

@Preview(showBackground = true)
@Composable
fun PreviewDialogConfirmLight() {
    ConsultaInventarioTheme(darkTheme = false) {
        DialogConfirm(
            title = "Confirmación",
            message = "¿Está seguro de realizar esta acción?",
            onPositiveCallback = { },
            onNegativeCallback = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDialogConfirmDark() {
    ConsultaInventarioTheme(darkTheme = true) {
        DialogConfirm(
            title = "Confirmación",
            message = "¿Está seguro de realizar esta acción?",
            onPositiveCallback = { },
            onNegativeCallback = { }
        )
    }
}
