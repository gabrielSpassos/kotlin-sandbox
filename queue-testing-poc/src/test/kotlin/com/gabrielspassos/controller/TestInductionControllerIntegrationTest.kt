package com.gabrielspassos.controller

import com.gabrielspassos.TestcontainersConfiguration
import com.gabrielspassos.controller.response.TestInductionExchangeJobResponse
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
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestTemplate
import org.testcontainers.mockserver.MockServerContainer

@Import(TestcontainersConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestInductionControllerIntegrationTest {

    @LocalServerPort
    var port: Int = 0

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
                          "date": "2026-08-13T14:05:36",
                          "usd": {
                            "brl": 5.19
                          }
                        }
                        """.trimIndent()
                    )
            )
    }

    @Test
    fun shouldTriggerExchangeJobAndSaveUser() {
        val testId = "test-induction-trigger-exchange-job-it-test"

        val url = "http://localhost:$port/v1/test-induction/exchange-job?id=$testId"
        val response = RestTemplate().postForEntity(url, null, TestInductionExchangeJobResponse::class.java)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertNotNull(response.body?.userId)
        assertEquals("username-test-induction-$testId", response.body?.username)
    }

    @TestConfiguration(proxyBeanMethods = false)
    class MockExchangeClientConfig {

        @Bean
        @Primary
        fun mockExchangeRestClient(builder: RestClient.Builder, mockServerContainer: MockServerContainer): RestClient {
            return builder
                .baseUrl(mockServerContainer.endpoint)
                .build()
        }

    }

}
