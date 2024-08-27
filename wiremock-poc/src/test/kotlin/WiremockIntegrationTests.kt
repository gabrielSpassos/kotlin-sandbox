
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.common.ContentTypes.ACCEPT
import com.github.tomakehurst.wiremock.common.ContentTypes.APPLICATION_JSON
import com.github.tomakehurst.wiremock.common.ContentTypes.CONTENT_TYPE
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.UUID
import kotlin.test.assertEquals

class WiremockIntegrationTests {

    private val port: Int = 8089
    private val wireMockServer: WireMockServer = WireMockServer(options().port(port))

    @BeforeEach
    fun setup() {
        wireMockServer.start()
    }

    @AfterEach
    fun cleanUp() {
        wireMockServer.stop()
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
        wireMockServer.stubFor(get(urlEqualTo("/v1/people/$id"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                .withBody(responseBody)))

        val request = HttpRequest.newBuilder()
            .uri(URI("http://localhost:$port/v1/people/$id"))
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(ACCEPT, APPLICATION_JSON)
            .GET()
            .build()
        val httpClient = HttpClient.newHttpClient()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

        assertEquals(200, response.statusCode())
        assertEquals(responseBody, response.body())
        assertEquals(APPLICATION_JSON, response.headers().map()[CONTENT_TYPE]?.first())
    }

}