package nl.birdly.graph.ui.transaction

import android.util.Range

data class BankingUiState(
    val graphData: GraphDataUiState,
    val listItems: List<TransactionUiState>
)

data class GraphDataUiState(
    val xRange: Pair<String, String>,
    val yRange: Pair<String, String>,
    val dataPoints: List<DataPointUiState>
)

data class DataPointUiState(val x: Float, val y: Float)