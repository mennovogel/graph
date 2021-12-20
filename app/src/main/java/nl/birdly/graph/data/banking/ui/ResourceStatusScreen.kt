package nl.birdly.graph.data.banking.ui

import androidx.compose.runtime.Composable
import nl.birdly.graph.ui.screen.ErrorScreen
import nl.birdly.graph.ui.screen.LoadingScreen
import nl.birdly.graph.util.ResourceStatus

@Composable
fun <T>ResourceStatusScreen(
    resourceStatus: ResourceStatus<T>,
    loadingScreen: @Composable () -> Unit = { LoadingScreen() },
    errorScreen: @Composable () -> Unit = { ErrorScreen() },
    successScreen: @Composable (T) -> Unit,
) {
    when (resourceStatus) {
        is ResourceStatus.Success<T> -> successScreen(resourceStatus.data)
        is ResourceStatus.Loading<T> -> { loadingScreen() }
        is ResourceStatus.Error<T> -> { errorScreen() }
    }
}
