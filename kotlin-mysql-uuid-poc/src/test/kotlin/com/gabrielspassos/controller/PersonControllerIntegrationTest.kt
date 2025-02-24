package com.gabrielspassos.controller

import com.gabrielspassos.repository.PersonRepository
import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.AfterEach
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
    private val peopleIds = mutableListOf<String>()

    @AfterEach
    fun cleanUp(): Unit {
        peopleIds.forEach { personId ->
            personRepository.findById(UUID.fromString(personId))
                .ifPresent { person -> personRepository.delete(person) }
        }
    }

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

        assertEquals(201, response.statusCode())
        assertNotNull(response.body())

        val responseBody = JSONObject(response.body())
        assertNotNull(responseBody.getString("id"))
        assertEquals(externalId.toString(), responseBody.getString("externalId"))
        assertEquals("John", responseBody.getString("name"))

        peopleIds.add(responseBody.getString("id"))
    }

    @Test
    fun shouldReturnPeople() {
        val url = "http://localhost:$randomServerPort/v1/people"

        val response = client.send(
            HttpRequest.newBuilder()
                .uri(URI(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build(),
            HttpResponse.BodyHandlers.ofString()
        )

        assertEquals(200, response.statusCode())
        assertNotNull(response.body())

        val peopleArray = JSONArray(response.body())
        for (i in 0 until peopleArray.length()) {
            val person = peopleArray.getJSONObject(i)
            assertNotNull(person.getString("id"))
            assertNotNull(person.getString("externalId"))
            assertNotNull(person.getString("name"))
        }
    }

    @Test
    fun shouldReturnPersonById() {
        val createPersonResponse = createPerson()
        assertEquals(201, createPersonResponse.statusCode())
        assertNotNull(createPersonResponse.body())
        val createPersonResponseBody = JSONObject(createPersonResponse.body())
        val id = createPersonResponseBody.getString("id")

        val url = "http://localhost:$randomServerPort/v1/people/$id"

        val response = client.send(
            HttpRequest.newBuilder()
                .uri(URI(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build(),
            HttpResponse.BodyHandlers.ofString()
        )

        assertEquals(200, response.statusCode())
        assertNotNull(response.body())
        val person = JSONObject(response.body())
        assertEquals(id, person.getString("id"))
        assertEquals(createPersonResponseBody.getString("externalId"), person.getString("externalId"))
        assertEquals(createPersonResponseBody.getString("name"), person.getString("name"))

        peopleIds.add(person.getString("id"))
    }

    @Test
    fun shouldReturnPersonByExternalId() {
        val createPersonResponse = createPerson()
        assertEquals(201, createPersonResponse.statusCode())
        assertNotNull(createPersonResponse.body())
        val createPersonResponseBody = JSONObject(createPersonResponse.body())
        val externalId = createPersonResponseBody.getString("externalId")

        val url = "http://localhost:$randomServerPort/v1/people/externalId/$externalId"

        val response = client.send(
            HttpRequest.newBuilder()
                .uri(URI(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build(),
            HttpResponse.BodyHandlers.ofString()
        )

        assertEquals(200, response.statusCode())
        assertNotNull(response.body())
        val person = JSONObject(response.body())
        assertEquals(createPersonResponseBody.getString("id"), person.getString("id"))
        assertEquals(externalId, person.getString("externalId"))
        assertEquals(createPersonResponseBody.getString("name"), person.getString("name"))

        peopleIds.add(person.getString("id"))
    }

    private fun createPerson(): HttpResponse<String> {
        val externalId = UUID.randomUUID()
        val request = """{"externalId":"$externalId","name":"John"}"""
        val createPersonUrl = "http://localhost:$randomServerPort/v1/people"
        val response = client.send(
            HttpRequest.newBuilder()
                .uri(URI(createPersonUrl))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(request))
                .build(),
            HttpResponse.BodyHandlers.ofString()
        )
        return response
    }
}