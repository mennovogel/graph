package nl.birdly.graph.data

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import timber.log.Timber
import java.io.IOException
import java.util.*

class StubInterceptor(
    private val stringReader: StringReader
    ) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val path = ChainReader.readPath(chain)
        val response = try {
            stringReader.readAsString("$path.json")
        } catch (e: IOException) {
//            try {
//                AssetReader.readAsString(
//                    assets,
//                    url.encodedPath().substring(1).toLowerCase() + ".json"
//                )
//            } catch (e1: IOException) {
                Timber.d(
                    "Mock for ${
                        path.substring(1).lowercase(Locale.getDefault())
                    }.json not found in /assets folder."
                )

                // If no stub was found, proceed like a normal request
                return chain.proceed(chain.request())
//            }
        }

        // A stub was found, so we can use this stub.
        return Response.Builder()
            .code(200)
            .message(response)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(ResponseBody.create(MediaType.parse("application/json"), response))
            .addHeader("content-type", "application/json")
            .build()
    }

//    fun readPath(): String {
//
//    }
}