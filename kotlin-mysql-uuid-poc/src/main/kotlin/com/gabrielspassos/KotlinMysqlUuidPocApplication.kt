package com.gabrielspassos

import com.gabrielspassos.dao.PersonDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinMysqlUuidPocApplication(@Autowired private val personDAO: PersonDAO) : CommandLineRunner {
	override fun run(vararg args: String?) {
		personDAO.startPersonTable()
	}
}

fun main(args: Array<String>) {
	runApplication<KotlinMysqlUuidPocApplication>(*args)
}
