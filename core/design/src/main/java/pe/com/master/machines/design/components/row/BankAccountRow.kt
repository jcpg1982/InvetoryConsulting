package pe.com.master.machines.design.components.row

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetSixteen
import pe.com.master.machines.design.theme.ContentInsetTwelve
import pe.com.master.machines.design.theme.DynamicTextFourteen
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.utils.Utils.formatBalance
import pe.com.master.machines.model.BankAccount

@Composable
fun BankAccountRow(
    account: BankAccount,
    modifier: Modifier = Modifier
) {
    
    val maskedNumber = "**** ${account.accountNumber.takeLast(4)}"

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = ContentInsetEight,
                shape = RoundedCornerShape(ContentInsetTwelve)
            )
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(ContentInsetTwelve))
            .padding(ContentInsetSixteen)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                CustomText(
                    text = account.type,
                    fontSize = DynamicTextFourteen,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                CustomText(
                    text = "No. $maskedNumber",
                    fontSize = DynamicTextFourteen,
                    color = MaterialTheme.colorScheme.outline
                )
            }

            CustomText(
                text = "${account.currencySymbol} ${formatBalance(account.balance)}",
                fontSize = DynamicTextSixteen,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
