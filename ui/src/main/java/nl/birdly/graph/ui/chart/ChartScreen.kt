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
import nl.birdly.graph.ui.chart.linechart.LineChart

@Composable
fun GraphScreen() {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
            ,
            initialXRange = Range.create(20F, 80F),
            yRange = Range.create(0F, 100F),
            data = listOf(
                PointF(0f, 0f),
                PointF(10f, 5f),
                PointF(20f, 10f),
                PointF(30f, 20f),
                PointF(40f, 40f),
                PointF(50f, 50f),
                PointF(60f, 60f),
                PointF(70f, 80f),
                PointF(80f, 90f),
                PointF(90f, 95f),
                PointF(100f, 100f),
            ),
        )
    }
}