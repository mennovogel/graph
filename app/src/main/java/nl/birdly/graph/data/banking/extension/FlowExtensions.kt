package nl.birdly.graph.data.banking.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import nl.birdly.graph.util.ResourceStatus

fun <T : Any> Flow<T>.mapToResourceStatus(): Flow<ResourceStatus<T>> = map {
    ResourceStatus.Success(it) as ResourceStatus<T>
}.onStart {
    emit(ResourceStatus.Loading())
}.catch {
    emit(ResourceStatus.Error(it))
}