package nl.birdly.graph.ui.chart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nl.birdly.graph.ui.theme.GraphTheme

class ChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphTheme {
                GraphScreen()
            }
        }
    }
}