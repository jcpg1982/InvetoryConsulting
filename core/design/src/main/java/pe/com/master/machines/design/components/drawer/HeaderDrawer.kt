package pe.com.master.machines.design.components.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.R
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetEighty
import pe.com.master.machines.design.theme.ContentInsetFifty
import pe.com.master.machines.design.theme.ContentInsetSixteen
import pe.com.master.machines.design.theme.ContentInsetSixty
import pe.com.master.machines.design.theme.DynamicTextFourteen
import pe.com.master.machines.model.model.Data

@Composable
fun HeaderDrawer(
    data: Data,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(ContentInsetSixteen)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_playstore),
            contentDescription = "",
            modifier = Modifier.size(ContentInsetEighty)
        )

        Spacer(modifier = Modifier.height(ContentInsetEight))

        CustomText(
            modifier = Modifier
                .fillMaxWidth(),
            text = data.userName,
            color = Color.White,
            fontSize = DynamicTextFourteen,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(ContentInsetEight))

        CustomText(
            modifier = Modifier
                .fillMaxWidth(),
            text = "${data.userId}",
            color = Color.White,
            fontSize = DynamicTextFourteen,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun PreviewHeaderDrawerLight() {
    ConsultaInventarioTheme(
        darkTheme = false
    ) {
        HeaderDrawer(
            data = Data(
                userId = 1982,
                userName = "Jak Motero",
                listInventories = listOf()
            )
        )
    }
}

@Preview
@Composable
fun PreviewHeaderDrawerDark() {
    ConsultaInventarioTheme(
        darkTheme = true
    ) {
        HeaderDrawer(
            data = Data(
                userId = 1982,
                userName = "Jak Motero",
                listInventories = listOf()
            )
        )
    }
}

