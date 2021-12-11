package nl.birdly.graph.data.banking

import android.content.res.AssetManager
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import nl.birdly.graph.data.AssetReader
import nl.birdly.graph.data.StubInterceptor
import nl.birdly.graph.data.banking.domain.Transaction
import okhttp3.OkHttpClient
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


interface BankingApi {

    @GET("accounts/{user}/transactions")
    suspend fun transactions(@Path("user") user: Long): List<Transaction>

    object Builder {
        fun build(assetManager: AssetManager): BankingApi = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        StubInterceptor(
                            AssetReader(assetManager)
                        )
                    )
                    .build()
            )
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(DateAdapter())
                    .build()
            ))
            .baseUrl("https://www.birdly.nl")
            .build()
            .create(BankingApi::class.java)
    }
}