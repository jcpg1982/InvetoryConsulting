package pe.com.master.machines.model

import java.util.UUID

data class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val description: String,
    val amount: Double,
    val date: String,
    val isIncome: Boolean
)
