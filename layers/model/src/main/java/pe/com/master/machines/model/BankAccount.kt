package pe.com.master.machines.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class BankAccount(
    val id: String = UUID.randomUUID().toString(),
    val type: String,
    val currencySymbol: String,
    val balance: Double,
    val accountNumber: String
)
