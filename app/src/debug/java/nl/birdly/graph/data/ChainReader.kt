package nl.birdly.graph.data

import okhttp3.Interceptor
import java.util.*

object ChainReader {

    fun readPath(chain: Interceptor.Chain): String {
        val url = chain.request().url()
        var sym = ""
        val query: String? = if (url.encodedQuery() == null) {
            ""
        } else {
            url.encodedQuery()
        }
        if ("" != query) {
            sym = "/"
        }
        val fullPath = url.encodedPath() + sym + query
        return fullPath.substring(1).lowercase(Locale.getDefault())
    }
}