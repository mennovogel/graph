package nl.birdly.graph.util

import nl.birdly.graph.data.banking.domain.Amount

fun Amount.toDisplayableAmount(): String {
    return when (currency) {
        "EUR" -> "€ ${amount.format(2)}"
        else -> throw IllegalStateException("Do not know this currency $currency")
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)