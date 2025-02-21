package com.gabrielspassos.controller

import com.gabrielspassos.controller.request.PersonRequest
import com.gabrielspassos.entity.PersonEntity
import com.gabrielspassos.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/v1/people")
class PersonController (@Autowired private val personService: PersonService) {

    @GetMapping
    fun getAllPeople(): ResponseEntity<List<PersonEntity>> = ResponseEntity.ok(personService.getAllPersons())

    @PostMapping
    fun createPerson(@RequestBody personRequest: PersonRequest): ResponseEntity<PersonEntity> {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createPerson(personRequest))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: UUID): ResponseEntity<PersonEntity> {
        return ResponseEntity.ok(personService.getPersonById(id))
    }

    @GetMapping("/externalId/{externalId}")
    fun getByExternalId(@PathVariable("externalId") externalId: UUID): ResponseEntity<PersonEntity> {
        return ResponseEntity.ok(personService.getPersonByExternalId(externalId))
    }

}