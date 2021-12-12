package nl.birdly.graph.ui.chart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import nl.birdly.graph.data.banking.BankingApi
import nl.birdly.graph.data.banking.ui.ResourceStatusScreen
import nl.birdly.graph.ui.theme.GraphTheme
import nl.birdly.graph.util.ResourceStatus

class ChartActivity : ComponentActivity() {
    private val viewModel by viewModels<ChartViewModel> {
        ChartViewModel.Factory(BankingApi.Builder.build(applicationContext.assets), 123L)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphTheme {
                val state by viewModel.transactions.observeAsState(ResourceStatus.Loading())
                val stateVal = state
                ResourceStatusScreen(stateVal) {
                    GraphScreen(it)
                }
            }
        }
    }
}