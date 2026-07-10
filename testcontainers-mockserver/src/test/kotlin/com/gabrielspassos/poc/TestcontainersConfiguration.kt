package com.gabrielspassos.poc

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.mockserver.MockServerContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection(name = "mockserver")
    fun mockServerContainer(): MockServerContainer {
        val container = MockServerContainer(DockerImageName.parse("mockserver/mockserver:7.4.0"))
        container.start()
        return container
    }
}
