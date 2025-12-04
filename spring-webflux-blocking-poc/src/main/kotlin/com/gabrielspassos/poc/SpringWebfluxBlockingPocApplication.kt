package com.gabrielspassos.poc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringWebfluxBlockingPocApplication

fun main(args: Array<String>) {
	runApplication<SpringWebfluxBlockingPocApplication>(*args)
}
