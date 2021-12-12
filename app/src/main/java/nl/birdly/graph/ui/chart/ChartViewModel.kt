package nl.birdly.graph.ui.chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flow
import nl.birdly.graph.data.banking.BankingApi
import nl.birdly.graph.data.banking.domain.Transaction
import nl.birdly.graph.data.banking.extension.mapToResourceStatus
import nl.birdly.graph.util.ResourceStatus

class ChartViewModel(
    private val bankingApi: BankingApi,
    private val userId: Long
) : ViewModel() {

    val transactions: LiveData<ResourceStatus<List<Transaction>>> =
        flow {
            val value = bankingApi.transactions(userId)
            emit(value)
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