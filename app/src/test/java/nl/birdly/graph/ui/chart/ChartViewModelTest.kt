package nl.birdly.graph.ui.chart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import nl.birdly.graph.data.banking.BankingApi
import nl.birdly.graph.util.MainCoroutineRule
import nl.birdly.graph.util.ResourceStatus
import nl.birdly.graph.util.observeForTesting
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChartViewModelTest {

    private val api = mockk<BankingApi>()
    private val viewModel = ChartViewModel(api, 1L)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun before() {
        clearAllMocks()
    }

    @Test
    fun `Should resolve successful call`(): Unit = runBlocking {
        coEvery { api.transactions(any()) }.returns(listOf(mockk()))

        viewModel.transactions().observeForTesting {
            assertTrue(it.values[0] is ResourceStatus.Loading)
            assertTrue(it.values[1] is ResourceStatus.Success)
            assertEquals(
                1,
                (it.values[1] as ResourceStatus.Success).data.size
            )
        }
    }
}