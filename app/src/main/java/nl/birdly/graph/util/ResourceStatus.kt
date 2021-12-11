package nl.birdly.graph.util

sealed class ResourceStatus<out T : Any>(open val data: T? = null) {

    class Loading<out T : Any>(data: T? = null) : ResourceStatus<T>(data)
    class Success<out T : Any>(override val data: T) : ResourceStatus<T>(data)
    class Error<out T : Any>(val throwable: Throwable, data: T? = null) : ResourceStatus<T>(data)
}