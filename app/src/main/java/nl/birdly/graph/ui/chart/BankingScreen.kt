package nl.birdly.graph.ui.chart

import android.graphics.PointF
import android.util.Range
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.birdly.graph.ui.chart.linechart.LineChart
import nl.birdly.graph.ui.listitem.RegularListItem
import nl.birdly.graph.ui.transaction.BankingUiState
import nl.birdly.graph.ui.transaction.DataPointUiState
import nl.birdly.graph.ui.transaction.GraphDataUiState
import nl.birdly.graph.ui.transaction.TransactionUiState
import java.util.*

@Composable
fun BankingScreen(bankingUiState: BankingUiState) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        if (bankingUiState.listItems.isEmpty()) return@Surface

        Column {
            LineChart(
                initialXRange = Range.create(
                    bankingUiState.graphData.dataPoints.minOf { it.x },
                    bankingUiState.graphData.dataPoints.maxOf { it.x }
                ),
                yRange = Range.create(
                    bankingUiState.graphData.dataPoints.minOf { it.y },
                    bankingUiState.graphData.dataPoints.maxOf { it.y }
                ),
                data = bankingUiState.graphData.dataPoints.map { PointF(it.x, it.y) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            LazyColumn(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var currentDate: String? = null

                items(bankingUiState.listItems) { transaction: TransactionUiState ->
                    val transactionDate = transaction.time
                    if (transactionDate != currentDate) {
                        currentDate = transactionDate
                        Text(transactionDate)
                    }
                    RegularListItem(
                        startTitle = transaction.name,
                        startSubtitle = transaction.description,
                        endTitle = transaction.amount
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 320, heightDp = 440)
@Composable
private fun BankingScreenPreview() {
    BankingScreen(
        bankingUiState = BankingUiState(
            GraphDataUiState(
                Pair("Oct 31, 2021", "Nov 7, 2021"),
                Pair("0,00", "10,00"),
                listOf(DataPointUiState(0f, 0f), DataPointUiState(1f, 1f))
            ),
            listOf(
                TransactionUiState(
                    1L,
                    "€ 10,00",
                    "name",
                    "description",
                    "Nov 7, 2021"
                )
            )
        )
    )
}