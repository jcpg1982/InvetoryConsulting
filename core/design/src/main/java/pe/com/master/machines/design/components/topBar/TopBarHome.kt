package pe.com.master.machines.design.components.topBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.design.theme.ContentInsetEight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(
    title: String,
    onClickNavigation: () -> Unit
) {

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            CustomText(
                modifier = Modifier.padding(start = ContentInsetEight),
                text = title,
                color = Color.White
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Open Drawer",
                modifier = Modifier.clickable {
                    onClickNavigation()
                }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = Color.White
        )
    )

}

@Preview
@Composable
fun PreviewTopBarHomeLight() {
    ConsultaInventarioTheme(
        darkTheme = false
    ) {
        TopBarHome(
            title = "Ayuda",
            onClickNavigation = {}
        )
    }
}

@Preview
@Composable
fun PreviewTopBarHomeDark() {
    ConsultaInventarioTheme(
        darkTheme = true
    ) {
        TopBarHome(
            title = "Ayuda",
            onClickNavigation = {}
        )
    }
}
