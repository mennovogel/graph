package nl.birdly.graph.data.banking.domain

import java.util.*

data class Transaction(
    val id: Long,
    val amount: Amount,
    val name: String,
    val description: String,
    val time: Date,
)