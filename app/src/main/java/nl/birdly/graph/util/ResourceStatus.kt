package nl.birdly.graph.util

sealed class ResourceStatus<T>(open val data: T? = null) {

    class Loading<T>(data: T? = null) : ResourceStatus<T>(data)
    class Success<T>(override val data: T) : ResourceStatus<T>(data)
    class Error<T>(val throwable: Throwable, data: T? = null) : ResourceStatus<T>(data)
}