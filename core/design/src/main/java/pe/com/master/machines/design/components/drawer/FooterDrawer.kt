package pe.com.master.machines.design.components.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.common.ConstantsSystemProperties.versionCodeDevice
import pe.com.master.machines.common.ConstantsSystemProperties.versionNameDevice
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ConsultaInventarioTheme
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetFour
import pe.com.master.machines.design.theme.DynamicTextTen
import pe.com.master.machines.design.theme.DynamicTextTwelve

@Composable
fun FooterDrawer() {

    val versionApp by remember {
        mutableStateOf(String.format("versión: %s (%s)", versionNameDevice, versionCodeDevice))
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(ContentInsetEight)
    ) {
        CustomText(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Powered by KaMiSa",
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = DynamicTextTwelve
        )

        Spacer(modifier = Modifier.height(ContentInsetFour))

        CustomText(
            modifier = Modifier
                .fillMaxWidth(),
            text = versionApp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = DynamicTextTen
        )
    }
}

@Preview
@Composable
fun PreviewDarkFooterDrawer() {
    ConsultaInventarioTheme(
        darkTheme = true
    ) {
        FooterDrawer()
    }
}

@Preview
@Composable
fun PreviewLightFooterDrawer() {
    ConsultaInventarioTheme(
        darkTheme = false
    ) {
        FooterDrawer()
    }
}
