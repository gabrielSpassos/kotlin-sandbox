package com.gabrielspassos.testcontainers_mockserver

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<TestcontainersMockserverApplication>().with(TestcontainersConfiguration::class).run(*args)
}
