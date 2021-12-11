package nl.birdly.graph.ui.chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import nl.birdly.graph.data.banking.BankingApi
import nl.birdly.graph.data.banking.domain.Transaction
import nl.birdly.graph.data.banking.extension.mapToResourceStatus
import nl.birdly.graph.util.ResourceStatus

class ChartViewModel(
    private val bankingApi: BankingApi,
    private val userId: Long
) : ViewModel() {

    fun transactions(): LiveData<ResourceStatus<List<Transaction>>> =
        flow {
            val value = bankingApi.transactions(userId)
            emit(value)
        }.mapToResourceStatus()
            .asLiveData()

    class Factory(
        private val bankingApi: BankingApi,
        private val userId: Long,
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChartViewModel(bankingApi, userId) as T
        }
    }
}