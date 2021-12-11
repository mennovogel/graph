package nl.birdly.graph.data

import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class AssetReader(
    private val assetManager: AssetManager
) : StringReader {

    @Throws(IOException::class)
    override fun readAsString(assetFile: String): String {
        val stream = assetManager.open(assetFile)
        return readAsString(stream)
    }

    @Throws(IOException::class)
    private fun readAsString(stream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(stream))
        var line: String?
        val result = StringBuilder()
        while (reader.readLine().also { line = it } != null) {
            result.append(line)
        }
        return result.toString()
    }
}
