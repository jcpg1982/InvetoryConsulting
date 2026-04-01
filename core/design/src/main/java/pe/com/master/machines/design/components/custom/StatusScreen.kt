package pe.com.master.machines.design.components.custom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ContentInsetFiftySix
import pe.com.master.machines.design.theme.ContentInsetForty
import pe.com.master.machines.design.theme.ContentInsetOneHundredTwenty
import pe.com.master.machines.design.theme.ContentInsetSixteen
import pe.com.master.machines.design.theme.ContentInsetThirtyTwo
import pe.com.master.machines.design.theme.ContentInsetTwentyFour
import pe.com.master.machines.design.theme.DynamicTextEighteen

@Composable
fun StatusScreen(
    title: String,
    description: String,
    icon: ImageVector,
    buttonLabel: String? = null,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ContentInsetThirtyTwo),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            icon,
            null,
            modifier = Modifier.size(ContentInsetOneHundredTwenty),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(ContentInsetTwentyFour))
        CustomText(
            title,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(ContentInsetSixteen))
        CustomText(
            description,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 10
        )
        Spacer(Modifier.height(ContentInsetForty))
        buttonLabel?.let {
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ContentInsetFiftySix),
                shape = RoundedCornerShape(ContentInsetSixteen)
            ) {
                CustomText(
                    it,
                    fontSize = DynamicTextEighteen,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
