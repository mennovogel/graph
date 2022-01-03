package nl.birdly.graph.ui.chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import nl.birdly.graph.data.banking.BankingApi
import nl.birdly.graph.data.banking.domain.Transaction
import nl.birdly.graph.data.banking.extension.mapToResourceStatus
import nl.birdly.graph.ui.transaction.BankingUiState
import nl.birdly.graph.ui.transaction.DataPointUiState
import nl.birdly.graph.ui.transaction.GraphDataUiState
import nl.birdly.graph.ui.transaction.TransactionUiState
import nl.birdly.graph.util.ResourceStatus
import nl.birdly.graph.util.toDisplayableAmount
import nl.birdly.graph.util.toDisplayableDate

class ChartViewModel(
    private val bankingApi: BankingApi,
    private val userId: Long
) : ViewModel() {

    val transactions: LiveData<ResourceStatus<BankingUiState>> =
        flow {
            val transactions = bankingApi.transactions(userId)
            emit(transactions.sortedByDescending { it.time })
        }.map { list ->
            list.toBankingUiState()
        }.mapToResourceStatus()
            .asLiveData(viewModelScope.coroutineContext)

    class Factory(
        private val bankingApi: BankingApi,
        private val userId: Long,
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChartViewModel(bankingApi, userId) as T
        }
    }
}

private fun List<Transaction>.toBankingUiState(): BankingUiState {
    val data = sortedBy { it.time }
    return BankingUiState(
        GraphDataUiState(
            xRange = Pair(
                data.minOf { it.time }.toDisplayableDate(),
                data.maxOf { it.time }.toDisplayableDate()
            ),
            yRange = Pair(
                data.minOf { it.amount.amount }.toString(),// TODO: Convert to displayable amount
                data.maxOf { it.amount.amount }.toString(),
            ),
            map {
                DataPointUiState(
                    it.time.time.toFloat(),
                    it.amount.amount.toFloat()
                )
            }
        ),
        map {
            it.toTransactionUiState()
        }
    )
}

private fun Transaction.toTransactionUiState() = TransactionUiState(
    id,
    amount.toDisplayableAmount(),
    name,
    description,
    time.toDisplayableDate()
)