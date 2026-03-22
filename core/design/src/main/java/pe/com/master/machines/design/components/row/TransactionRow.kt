package pe.com.master.machines.design.components.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.DynamicTextFourteen
import pe.com.master.machines.design.theme.DynamicTextTwelve
import pe.com.master.machines.design.theme.warning
import pe.com.master.machines.design.theme.success
import pe.com.master.machines.design.utils.Utils.formatBalance
import pe.com.master.machines.model.Transaction

@Composable
fun TransactionRow(transaction: Transaction, symbol: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            CustomText(
                text = transaction.description,
                fontSize = DynamicTextFourteen,
                fontWeight = FontWeight.Medium
            )
            CustomText(
                text = transaction.date,
                fontSize = DynamicTextTwelve,
                color = MaterialTheme.colorScheme.outline
            )
        }

        val color = if (transaction.isIncome) success else warning
        val prefix = if (transaction.isIncome) "+" else "-"

        CustomText(
            text = "$prefix $symbol ${formatBalance(transaction.amount)}",
            fontSize = DynamicTextFourteen,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}
