package nl.birdly.graph.data

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class StubInterceptorTest {

    @Test
    fun `should intercept valid request`() {

        val stubInterceptor = StubInterceptor(mockk {
            every { readAsString("accounts/123/transactions.json") } returns VALID_JSON
        })

        val response = stubInterceptor.intercept(mockk())

        assertEquals(VALID_JSON, response.body()?.string())
    }

    companion object {
        private const val VALID_JSON = "{\"valid\": \"json\"}"
    }
}