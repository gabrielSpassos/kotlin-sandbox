package com.gabrielspassos.controller

import com.gabrielspassos.controller.request.PersonRequest
import com.gabrielspassos.entity.PersonEntity
import com.gabrielspassos.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
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
    fun getAllPeople(): List<PersonEntity> = personService.getAllPersons()

    @PostMapping
    fun createPerson(@RequestBody personRequest: PersonRequest) {
        personService.createPerson(personRequest)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: UUID): PersonEntity {
        return personService.getPersonById(id)
    }

    @GetMapping("/externalId/{externalId}")
    fun getByExternalId(@PathVariable("externalId") externalId: UUID): PersonEntity {
        return personService.getPersonByExternalId(externalId)
    }

}