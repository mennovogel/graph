package nl.birdly.graph.data

import java.io.IOException

interface StringReader {

    @Throws(IOException::class)
    fun readAsString(assetFile: String): String
}
