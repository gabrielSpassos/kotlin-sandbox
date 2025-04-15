package com.gabrielspassos.controller.v1

import com.gabrielspassos.DocumentDbPocApplication
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [DocumentDbPocApplication::class]
)
class ProductControllerIntegrationTest {

    @Test
    fun shouldCreateProduct() {
        val httpClient = HttpClient.newHttpClient()
        val requestBody = this::class.java.classLoader.getResourceAsStream("create-product.json")?.bufferedReader()?.use { it.readText() }
        val request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/v1/products"))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody ?: ""))
            .header("Content-Type", "application/json")
            .build()
        val response = httpClient.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        )

        assertEquals(200, response.statusCode())
        assertNotNull(response.body())
    }
}
