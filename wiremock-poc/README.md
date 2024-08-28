# Mock Server POC

1. [Wiremock](https://wiremock.org/)
2. [Mock Server](https://www.mock-server.com/)

## Output

### Wiremock

```maven
<dependency>
    <groupId>org.wiremock</groupId>
    <artifactId>wiremock</artifactId>
    <version>3.9.1</version>
    <scope>test</scope>
</dependency>
```

Vulnerability
Dependency maven:commons-fileupload:commons-fileupload:1.5 is vulnerable

CVE-2023-24998,  Score: 7.5

Apache Commons FileUpload prior to 1.5.0.redhat-00001 does not limit the number of request parts to be processed, resulting in the possibility of an attacker triggering a Denial of Service (DoS) with a malicious upload or series of uploads. Note that, like all the file upload limits, the new configuration option (FileUploadBase#setFileCountMax) is not enabled by default and must be explicitly configured.

Read More: https://devhub.checkmarx.com/cve-details/CVE-2023-24998?utm_source=jetbrains&utm_medium=referral

Results powered by Checkmarx ©

1. Create server: `private val wireMockServer: WireMockServer = WireMockServer(options().port(port))`
2. Mock Get 
```kotlin
wireMockServer.stubFor(get(urlEqualTo("/v1/people/$id"))
    .willReturn(aResponse()
        .withStatus(200)
        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
        .withBody(responseBody)))
```
3. Mock Post
```kotlin
wireMockServer.stubFor(post(urlEqualTo("/v1/login"))
    .withRequestBody(equalToJson(requestBody))
    .willReturn(aResponse()
        .withStatus(201)
        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
        .withBody(responseBody)))
```
4. Mock different response (Retry Scenario)
```kotlin
wireMockServer.stubFor(get(urlEqualTo("/v1/people/$id"))
    .inScenario("get people with error")
    .whenScenarioStateIs(STARTED)
    .willReturn(aResponse()
        .withStatus(400)
        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
        .withBody(error))
    .willSetStateTo("success"))

wireMockServer.stubFor(get(urlEqualTo("/v1/people/$id"))
    .inScenario("get people with error")
    .whenScenarioStateIs("success")
    .willReturn(aResponse()
        .withStatus(200)
        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
        .withBody(responseBody)))
```

### Mock Server

```maven
<dependency>
    <groupId>org.mock-server</groupId>
    <artifactId>mockserver-netty</artifactId>
    <version>5.15.0</version>
    <scope>test</scope>
</dependency>
```

Vulnerability
Dependency maven:io.netty:netty-codec-http:4.1.86.Final is vulnerable

Upgrade to 4.1.108.final

CVE-2024-29025,  Score: 5.3

Netty is an asynchronous event-driven network application framework for rapid development of maintainable high performance protocol servers & clients. The `HttpPostRequestDecoder` can be tricked to accumulate data. While the decoder can store items on the disk if configured so, there are no limits to the number of fields the form can have, an attacher can send a chunked post consisting of many small fields that will be accumulated in the `bodyListHttpData` list. The decoder cumulates bytes in the `undecodedChunk` buffer until it can decode a field, this field can cumulate data without limits. This vulnerability is fixed in 4.1.108.Final.

Read More: https://devhub.checkmarx.com/cve-details/CVE-2024-29025?utm_source=jetbrains&utm_medium=referral

Results powered by Checkmarx ©

Dependency maven:io.netty:netty-codec-http2:4.1.86.Final is vulnerable

Upgrade to 4.1.100.final

CVE-2023-44487,  Score: 5.3

The HTTP/2 protocol allows a denial of service (server resource consumption) because request cancellation can reset many streams quickly, as exploited in the wild in August through October 2023.

Read More: https://devhub.checkmarx.com/cve-details/CVE-2023-44487?utm_source=jetbrains&utm_medium=referral

Results powered by Checkmarx ©

Dependency maven:io.netty:netty-handler:4.1.86.Final is vulnerable

Upgrade to 4.1.94.final

CVE-2023-34462,  Score: 6.5

Netty is an asynchronous event-driven network application framework for rapid development of maintainable high performance protocol servers & clients. In versions through 4.1.93.Final, 5.0.0.Alpha1 through 5.0.0.Alpha5,  the `SniHandler` can allocate up to 16MB of heap for each channel during the TLS handshake. When the handler or the channel does not have an idle timeout, it can be used to make a TCP server using the `SniHandler` to allocate 16MB of heap. The `SniHandler` class is a handler that waits for the TLS handshake to configure a `SslHandler` according to the indicated server name by the `ClientHello` record. For this matter it allocates a `ByteBuf` using the value defined in the `ClientHello` record. Normally the value of the packet should be smaller than the handshake packet but there are not checks done here and the way the code is written, it is possible to craft a packet that makes the `SslClientHelloHandler`.

Read More: https://devhub.checkmarx.com/cve-details/CVE-2023-34462?utm_source=jetbrains&utm_medium=referral

Results powered by Checkmarx ©

Dependency maven:com.google.guava:guava:31.1-jre is vulnerable

Upgrade to 32.0.0-android

CVE-2023-2976,  Score: 7.1

Use of Java's default temporary directory for file creation in `FileBackedOutputStream` in Google Guava versions 1.0 through 31.1-jre on Unix systems and Android Ice Cream Sandwich allows other users and apps on the machine with access to the default Java temporary directory to be able to access the files created by the class. Even though the security vulnerability is fixed in version 32.0.0, we recommend using version 32.0.1 as version 32.0.0 breaks some functionality under Windows.


Read More: https://devhub.checkmarx.com/cve-details/CVE-2023-2976?utm_source=jetbrains&utm_medium=referral

Results powered by Checkmarx ©

1. Create server: `private val mockServer: ClientAndServer = startClientAndServer(port)`
2. Mock Get
```kotlin
mockServer
    ?.`when`(request().withMethod("GET").withPath("/v1/people/$id"))
    ?.respond(response()
        .withStatusCode(200)
        .withBody(responseBody)
        .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
    )
```
3. Mock Post
```kotlin
mockServer
    ?.`when`(request().withMethod("POST").withPath("/v1/login").withBody(requestBody))
    ?.respond(response()
        .withStatusCode(201)
        .withBody(responseBody)
        .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
    )
```
4. Mock different response (Retry Scenario)
```kotlin
mockServer
    ?.`when`(request().withMethod("GET").withPath("/v1/people/$id"), Times.exactly(1))
    ?.respond(response()
        .withStatusCode(400)
        .withBody(error)
        .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
    )
mockServer
    ?.`when`(request().withMethod("GET").withPath("/v1/people/$id"), Times.exactly(1))
    ?.respond(response()
        .withStatusCode(200)
        .withBody(responseBody)
        .withHeader(CONTENT_TYPE, APPLICATION_JSON.toString())
    )
```

### Tests
1. First execution
```shell
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running MockServerIntegrationTests
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.132 s - in MockServerIntegrationTests
[INFO] Running WiremockIntegrationTests
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 5.306 s - in WiremockIntegrationTests
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ wiremock-poc ---
[INFO] Building jar: /Users/gpassos/Documents/Workspace/kotlin-sandbox/wiremock-poc/target/wiremock-poc-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-install-plugin:2.4:install (default-install) @ wiremock-poc ---
[INFO] Installing /Users/gpassos/Documents/Workspace/kotlin-sandbox/wiremock-poc/target/wiremock-poc-1.0-SNAPSHOT.jar to /Users/gpassos/.m2/repository/com/gabrielspassos/wiremock-poc/1.0-SNAPSHOT/wiremock-poc-1.0-SNAPSHOT.jar
[INFO] Installing /Users/gpassos/Documents/Workspace/kotlin-sandbox/wiremock-poc/pom.xml to /Users/gpassos/.m2/repository/com/gabrielspassos/wiremock-poc/1.0-SNAPSHOT/wiremock-poc-1.0-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11.174 s
[INFO] Finished at: 2024-08-28T11:51:58-03:00
[INFO] ------------------------------------------------------------------------
```

2. Other executions
```shell
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running MockServerIntegrationTests
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.12 s - in MockServerIntegrationTests
[INFO] Running WiremockIntegrationTests
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.278 s - in WiremockIntegrationTests
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ wiremock-poc ---
[INFO] Building jar: /Users/gpassos/Documents/Workspace/kotlin-sandbox/wiremock-poc/target/wiremock-poc-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-install-plugin:2.4:install (default-install) @ wiremock-poc ---
[INFO] Installing /Users/gpassos/Documents/Workspace/kotlin-sandbox/wiremock-poc/target/wiremock-poc-1.0-SNAPSHOT.jar to /Users/gpassos/.m2/repository/com/gabrielspassos/wiremock-poc/1.0-SNAPSHOT/wiremock-poc-1.0-SNAPSHOT.jar
[INFO] Installing /Users/gpassos/Documents/Workspace/kotlin-sandbox/wiremock-poc/pom.xml to /Users/gpassos/.m2/repository/com/gabrielspassos/wiremock-poc/1.0-SNAPSHOT/wiremock-poc-1.0-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.001 s
[INFO] Finished at: 2024-08-28T11:56:35-03:00
[INFO] ------------------------------------------------------------------------
```