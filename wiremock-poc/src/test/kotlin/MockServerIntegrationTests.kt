
import org.apache.http.HttpHeaders.ACCEPT
import org.apache.http.HttpHeaders.CONTENT_TYPE
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockserver.integration.ClientAndServer
import org.mockserver.integration.ClientAndServer.startClientAndServer
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.model.MediaType.APPLICATION_JSON
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.UUID
import kotlin.test.assertEquals

class MockServerIntegrationTests {
    private val port: Int = 8090
    private var mockServer: ClientAndServer? = null

    @BeforeEach
    fun setup() {
        mockServer = startClientAndServer(port)
    }

    @AfterEach
    fun cleanUp() {
        mockServer?.stop()
    }

    @Test
    fun shouldExecuteRequest() {
        val id = UUID.randomUUID().toString()
        val responseBody = """
            {
                "name": "Gabriel",
                "age": 27,
                "isMale": true
            }
        """.trimIndent()
        mockServer
            ?.`when`(request().withMethod("GET").withPath("/v1/people/$id"))
            ?.respond(response()
                .withStatusCode(200)
                .withBody(responseBody)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
            )

        val request = HttpRequest.newBuilder()
            .uri(URI("http://localhost:$port/v1/people/$id"))
            .header(CONTENT_TYPE, APPLICATION_JSON.toString())
            .header(ACCEPT, APPLICATION_JSON.toString())
            .GET()
            .build()
        val httpClient = HttpClient.newHttpClient()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

        assertEquals(200, response.statusCode())
        assertEquals(responseBody, response.body())
        assertEquals(APPLICATION_JSON.toString(), response.headers().map()[CONTENT_TYPE]?.first())
    }
}