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
import nl.birdly.graph.data.banking.domain.Amount
import nl.birdly.graph.data.banking.domain.Transaction
import nl.birdly.graph.ui.chart.linechart.LineChart
import nl.birdly.graph.ui.listitem.RegularListItem
import nl.birdly.graph.util.toDisplayableAmount
import nl.birdly.graph.util.toDisplayableDate
import java.util.*

@Composable
fun BankingScreen(transactions: List<Transaction>) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        if (transactions.isEmpty()) return@Surface

        Column {
            var amount = 200.0
            val data = transactions
                .sortedBy { it.time }
                .map {
                    amount += it.amount.amount
                    PointF(it.time.time.toFloat(), amount.toFloat())
                }
            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                initialXRange = Range.create(
                    data.minOf { it.x },
                    data.maxOf { it.x }
                ),
                yRange = Range.create(
                    data.minOf { it.y },
                    data.maxOf { it.y }
                ),
                data = data
            )
            LazyColumn(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var currentDate: String? = null

                items(transactions) { transaction: Transaction ->
                    val transactionDate = transaction.time.toDisplayableDate()
                    if (transactionDate != currentDate) {
                        currentDate = transactionDate
                        Text(transactionDate)
                    }
                    RegularListItem(
                        startTitle = transaction.name,
                        startSubtitle = transaction.description,
                        endTitle = transaction.amount.toDisplayableAmount()
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
        transactions = listOf(
            Transaction(
                1L,
                Amount("EUR", 10.00),
                "name",
                "description",
                Calendar.getInstance().time
            )
        )
    )
}