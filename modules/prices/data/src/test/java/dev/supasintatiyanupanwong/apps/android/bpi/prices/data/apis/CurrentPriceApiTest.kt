package dev.supasintatiyanupanwong.apps.android.bpi.prices.data.apis

import dev.supasintatiyanupanwong.apps.android.bpi.base.data.services.ClientProvider
import dev.supasintatiyanupanwong.apps.android.bpi.modules.prices.data.apis.CurrentPriceApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.nio.charset.StandardCharsets

class CurrentPriceApiTest {

    private lateinit var server: MockWebServer
    private lateinit var api: CurrentPriceApi

    @Before
    fun setup() {
        server = MockWebServer()
        api = ClientProvider()
            .provide(server.url("/").toString())
            .create(CurrentPriceApi::class.java)
    }

    @Test
    fun getCurrentPrice() {
        runBlocking {
            enqueueResponse()

            val res = api.getCurrentPrice()

            assert(res != null)

            assertEquals(res?.time?.updatedAt, 1686584040000)
            assertEquals(res?.bpi?.usd?.rate, "25,829.6180")
            assertEquals(res?.bpi?.gbp?.rate, "21,583.0222")
            assertEquals(res?.bpi?.eur?.rate, "25,161.8191")
        }
    }

    @After
    fun teardown() {
        server.shutdown()
    }


    private fun enqueueResponse() {
        val source = javaClass.classLoader
            ?.getResourceAsStream("api-responses/currentprice.json")
            ?.source()
            ?.buffer()

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(source?.readString(StandardCharsets.UTF_8) ?: return)
        )
    }

}
