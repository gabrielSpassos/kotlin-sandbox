package com.gabrielspassos.poc.client

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExchangeClientE2ETest {

    @Autowired
    lateinit var exchangeClient: ExchangeClient

    @Test
    fun shouldFetchExchangeRates() {
        val response = exchangeClient.usdToBrl

        assertNotNull(response)
        assertNotNull(response.date)
        assertNotNull(response.usd)
        assertNotNull(response.usd.brl)
    }

}