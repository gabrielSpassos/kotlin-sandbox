package com.gabrielspassos.poc.client

import com.gabrielspassos.poc.TestcontainersConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.mockserver.client.MockServerClient
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestClient
import org.testcontainers.mockserver.MockServerContainer
import java.math.BigDecimal

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class ExchangeClientIntegrationTest {

    @Autowired
    lateinit var exchangeClient: ExchangeClient

    @Autowired
    lateinit var mockServerContainer: MockServerContainer

    @BeforeEach
    fun setupMockServer() {
        MockServerClient(mockServerContainer.host, mockServerContainer.serverPort)
            .`when`(
                request()
                    .withMethod("GET")
                    .withPath("/npm/@fawazahmed0/currency-api@latest/v1/currencies/usd.json")
            )
            .respond(
                response()
                    .withStatusCode(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(
                        """
                        {
                          "date": "2026-07-08",
                          "usd": {
                            "brl": 6.54
                          }
                        }
                        """.trimIndent()
                    )
            )
    }

    @Test
    fun shouldFetchExchangeRates() {
        val response = exchangeClient.usdToBrl

        assertNotNull(response)
        assertEquals("2026-07-08", response.date)
        assertNotNull(response.usd)
        assertEquals(BigDecimal("6.54"), response.usd.brl)
    }

    @TestConfiguration(proxyBeanMethods = false)
    class MockExchangeClientConfig {

        @Bean
        @Primary
        fun exchangeRestClient(builder: RestClient.Builder, mockServerContainer: MockServerContainer): RestClient {
            return builder
                .baseUrl(mockServerContainer.endpoint)
                .build()
        }

    }
}
