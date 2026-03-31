package pe.com.master.machines.design.components.dialogs

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.model.enums.DialogType

@Composable
fun DialogAlert(
    title: String,
    message: String,
    isCancelable: Boolean = false,
    textPositiveButton: String = "Aceptar",
    textColorPositiveButton: Color = MaterialTheme.colorScheme.onPrimary,
    backgroundColorPositiveButton: Color = MaterialTheme.colorScheme.primary,
    onPositiveCallback: () -> Unit,
    onDismissDialog: () -> Unit = {}
) {
    CustomDefaultDialog<Any>(
        title = title,
        message = message,
        dialogType = DialogType.ALERT,
        isCancelable = isCancelable,
        textPositiveButton = textPositiveButton,
        textColorPositiveButton = textColorPositiveButton,
        backgroundColorPositiveButton = backgroundColorPositiveButton,
        onPositiveCallback = { onPositiveCallback() },
        onNegativeCallback = { onDismissDialog() },
        onDismissDialog = { onDismissDialog() }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDialogAlertLight() {
    ConsultaInventarioTheme(darkTheme = false) {
        DialogAlert(
            title = "Error en el servidor",
            message = "Este es el cuerpo del dialogo con el tema claro aplicado por defecto.",
            onPositiveCallback = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDialogAlertDark() {
    ConsultaInventarioTheme(darkTheme = true) {
        DialogAlert(
            title = "Error en el servidor",
            message = "Este es el cuerpo del dialogo con el tema oscuro aplicado por defecto.",
            onPositiveCallback = { }
        )
    }
}
