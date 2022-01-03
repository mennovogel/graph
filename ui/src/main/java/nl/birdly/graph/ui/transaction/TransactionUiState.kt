package nl.birdly.graph.ui.transaction

data class TransactionUiState(
    val id: Long,
    val amount: String,
    val name: String,
    val description: String,
    val time: String
)