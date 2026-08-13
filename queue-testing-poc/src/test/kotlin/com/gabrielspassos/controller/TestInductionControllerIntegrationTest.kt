package com.gabrielspassos.controller

import com.gabrielspassos.TestcontainersConfiguration
import com.gabrielspassos.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockserver.client.MockServerClient
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestClient
import org.testcontainers.mockserver.MockServerContainer

@Import(TestcontainersConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestInductionControllerIntegrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var mockServerContainer: MockServerContainer

    @Autowired
    lateinit var userRepository: UserRepository

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
                          "date": "2026-08-13",
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

        val response = restTemplate.postForEntity("/v1/test-induction/exchange-job?id=$testId", null, String::class.java)

        assertEquals(HttpStatus.OK, response.statusCode)

        val users = userRepository.findAll()
        val found = users.any { it.name == "username-test-induction-$testId" }
        assertTrue(found, "expected user from test induction to be saved")
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
