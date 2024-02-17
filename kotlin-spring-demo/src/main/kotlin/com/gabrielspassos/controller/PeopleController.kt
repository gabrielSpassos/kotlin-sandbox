package com.gabrielspassos.controller

import com.gabrielspassos.dto.PersonDTO
import com.gabrielspassos.service.PersonService
import org.springframework.web.bind.annotation.*

@RestController
class PeopleController (val personService: PersonService) {

    @GetMapping("/people")
    fun getPeople(): List<PersonDTO> {
        return personService.findPeople()
    }

    @GetMapping("/people/{id}")
    fun getPersonById(@PathVariable("id") id: String): PersonDTO? {
        return personService.findPersonById(id)
    }

    @PostMapping("/people")
    fun createPerson(@RequestBody person: PersonDTO): PersonDTO? {
        return personService.savePerson(person)
    }

}