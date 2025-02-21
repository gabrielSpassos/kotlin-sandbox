package com.gabrielspassos.controller

import com.gabrielspassos.repository.PersonRepository
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpHeaders.ACCEPT
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerIntegrationTest(@Autowired private val personRepository: PersonRepository) {

    @LocalServerPort
    var randomServerPort: Int = 0

    private val client = HttpClient.newHttpClient()

    @Test
    fun shouldCreatePerson() {
        val externalId = UUID.randomUUID()
        val request = """{"externalId":"$externalId","name":"John"}"""

        val url = "http://localhost:$randomServerPort/v1/people"
        val response = client.send(
            HttpRequest.newBuilder()
                .uri(URI(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(request))
                .build(),
            HttpResponse.BodyHandlers.ofString()
        )

        assertEquals(200, response.statusCode())
        assertNotNull(response.body())

        val responseBody = JSONObject(response.body())
        assertNotNull(responseBody.get("id"))
        assertEquals(externalId, responseBody.get("externalId"))
        assertEquals("John", responseBody.get("name"))
    }
}