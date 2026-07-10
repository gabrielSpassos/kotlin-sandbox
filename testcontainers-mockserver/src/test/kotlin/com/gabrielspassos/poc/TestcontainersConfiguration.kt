package com.gabrielspassos.poc

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.mockserver.MockServerContainer
import org.testcontainers.utility.DockerImageName

@Testcontainers
@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    fun mockServerContainer(): MockServerContainer {
        val container = MockServerContainer(DockerImageName.parse("mockserver/mockserver:7.4.0"))
        container.start()
        return container
    }
}
