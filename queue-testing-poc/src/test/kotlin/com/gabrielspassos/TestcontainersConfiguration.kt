package com.gabrielspassos

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.kafka.KafkaContainer
import org.testcontainers.mockserver.MockServerContainer
import org.testcontainers.utility.DockerImageName

@Testcontainers
@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	fun kafkaContainer(): KafkaContainer {
		return KafkaContainer(DockerImageName.parse("apache/kafka-native:4.3.1"))
	}

	@Bean
	fun mockServerContainer(): MockServerContainer {
		val container = MockServerContainer(DockerImageName.parse("mockserver/mockserver:7.4.0"))
		container.start()
		return container
	}

}
