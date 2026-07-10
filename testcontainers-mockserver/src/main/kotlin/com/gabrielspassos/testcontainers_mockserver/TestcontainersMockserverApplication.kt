package com.gabrielspassos.testcontainers_mockserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestcontainersMockserverApplication

fun main(args: Array<String>) {
	runApplication<TestcontainersMockserverApplication>(*args)
}
