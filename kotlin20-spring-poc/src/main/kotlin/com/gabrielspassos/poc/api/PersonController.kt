package com.gabrielspassos.poc.api

import com.gabrielspassos.poc.api.request.CreatePersonRequest
import com.gabrielspassos.poc.api.response.PersonResponse
import com.gabrielspassos.poc.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(@Autowired val personService: PersonService) {

    @PostMapping("/v1/people")
    fun createPerson(
        @RequestBody createPersonRequest: CreatePersonRequest
    ): ResponseEntity<PersonResponse> {
        val startTime = System.currentTimeMillis()
        try {
            val personResponse = personService.createPerson(createPersonRequest)

            val timeTaken = System.currentTimeMillis() - startTime
            println("Created person ${createPersonRequest.name}. timeTaken=$timeTaken")

            return ResponseEntity.status(HttpStatus.CREATED).body(personResponse)
        } catch (e: Exception) {
            val timeTaken = System.currentTimeMillis() - startTime
            println("Error to create person ${createPersonRequest.name}. timeTaken=$timeTaken")
            throw e
        }
    }

    @GetMapping("/v1/people/{id}")
    fun getPersonById(
        @PathVariable id: String
    ): ResponseEntity<PersonResponse> {
        val startTime = System.currentTimeMillis()
        try {
            val personResponse = personService.findPersonById(id)

            val timeTaken = System.currentTimeMillis() - startTime
            println("Found person by id=$id. timeTaken=$timeTaken")

            return ResponseEntity.status(HttpStatus.OK).body(personResponse)
        } catch (e: Exception) {
            val timeTaken = System.currentTimeMillis() - startTime
            println("Error to find person by id=$id. timeTaken=$timeTaken")
            throw e
        }
    }
}