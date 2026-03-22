package pe.com.master.machines.design.components.topBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ConsultaInventarioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicTopBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            CustomText(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary, // Fondo Azul Corporativo
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Preview
@Composable
fun PreviewBasicTopBarLight(modifier: Modifier = Modifier) {
    ConsultaInventarioTheme(darkTheme = false) {
        BasicTopBar(title = "Esto es el titulo")
    }
}

@Preview
@Composable
fun PreviewBasicTopBarDark(modifier: Modifier = Modifier) {
    ConsultaInventarioTheme(darkTheme = true) {
        BasicTopBar(title = "Esto es el titulo")
    }
}
