package pe.com.master.machines.design.components.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ContentInsetSixteen
import pe.com.master.machines.design.theme.ContentInsetTwentyFour
import pe.com.master.machines.design.theme.DynamicTextFourteen
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.theme.DynamicTextTwelve
import pe.com.master.machines.design.theme.DynamicTextTwentyFour
import pe.com.master.machines.design.utils.Utils.formatBalance
import pe.com.master.machines.model.BankAccount

@Composable
fun AccountHeader(account: BankAccount) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(ContentInsetSixteen),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(ContentInsetTwentyFour)) {
            CustomText(
                text = account.type,
                fontSize = DynamicTextFourteen,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            CustomText(
                text = "No. ${account.accountNumber}",
                fontSize = DynamicTextSixteen,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(ContentInsetSixteen))
            CustomText(
                text = "Saldo disponible",
                fontSize = DynamicTextTwelve,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
            )
            CustomText(
                text = "${account.currencySymbol} ${formatBalance(account.balance)}",
                fontSize = DynamicTextTwentyFour,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}
