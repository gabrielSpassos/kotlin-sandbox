package com.gabrielspassos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DocumentDbPocApplication

fun main(args: Array<String>) {
	runApplication<DocumentDbPocApplication>(*args)
}
