package nl.birdly.graph.ui.chart

import android.graphics.PointF
import android.util.Range
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.birdly.graph.data.banking.domain.Transaction
import nl.birdly.graph.ui.chart.linechart.LineChart

@Composable
fun GraphScreen(transactions: List<Transaction>) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        if (transactions.isEmpty()) return@Surface
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            initialXRange = Range.create(
                transactions.minOf { it.time.time }.toFloat(),
                transactions.maxOf { it.time.time }.toFloat()
            ),
            yRange = Range.create(
                transactions.minOf { it.amount.amount }.toFloat(),
                transactions.maxOf { it.amount.amount }.toFloat()
            ),
            data = transactions.map { transaction ->
                PointF(transaction.time.time.toFloat(), transaction.amount.amount.toFloat())
            },
        )
    }
}