package com.gabrielspassos.service

import com.gabrielspassos.controller.request.PersonRequest
import com.gabrielspassos.entity.PersonEntity
import com.gabrielspassos.exception.NotFoundException
import com.gabrielspassos.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jdbc.core.JdbcAggregateTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PersonService (@Autowired private val personRepository: PersonRepository,
                     @Autowired private val jdbcTemplate: JdbcAggregateTemplate) {

    fun getPersonById(id: UUID): PersonEntity {
        return personRepository.findById(id).orElseThrow { NotFoundException("Person not found") }
    }

    fun getPersonByExternalId(externalId: UUID): PersonEntity {
        return personRepository.findByExternalId(externalId).orElseThrow { NotFoundException("Person not found") }
    }

    fun createPerson(person: PersonRequest): PersonEntity {
        val personEntity = PersonEntity(
            id = UUID.randomUUID(),
            externalId = person.externalId,
            name = person.name,
        )
        return jdbcTemplate.insert(personEntity)
    }

    fun updatePerson(person: PersonEntity): PersonEntity {
        return personRepository.save(person)
    }

    fun deletePerson(id: UUID) {
        personRepository.deleteById(id)
    }

    fun getAllPersons(): List<PersonEntity> {
        return personRepository.findAll().toList()
    }

}