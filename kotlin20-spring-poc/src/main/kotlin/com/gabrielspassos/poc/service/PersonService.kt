package com.gabrielspassos.poc.service

import com.gabrielspassos.poc.api.request.CreatePersonRequest
import com.gabrielspassos.poc.api.response.PersonResponse
import com.gabrielspassos.poc.domain.model.Person
import com.gabrielspassos.poc.domain.repository.PersonRepository
import com.gabrielspassos.poc.exception.NotFoundException
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.jdbc.core.JdbcAggregateTemplate
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PersonService(
    @Autowired private val personRepository: PersonRepository,
    @Autowired private val jdbcTemplate: JdbcAggregateTemplate,
    @Autowired private val kafkaTemplate: KafkaTemplate<String, String>
) {

    @Value("\${kafka.person-topic}")
    private val personTopic = ""

    fun createPerson(createPersonRequest: CreatePersonRequest): PersonResponse {
        val person = Person(
            id = UUID.randomUUID().toString(),
            name = createPersonRequest.name,
            parentId = createPersonRequest.parentId
        )

        val savedPerson = jdbcTemplate.insert(person)

        val record = ProducerRecord(personTopic, savedPerson.name, savedPerson.id)
        kafkaTemplate.send(record)

        return findPersonById(savedPerson.id)
    }

    fun findPersonById(id: String): PersonResponse {
        val optionalPerson = personRepository.findById(id)

        if (optionalPerson.isEmpty) {
            throw NotFoundException("not found person by id $id")
        }

        val person = optionalPerson.get()

        val children = personRepository.findByParentId(person.id)
        val childrenResponse = buildChildrenResponse(children)

        return PersonResponse(
            id = person.id,
            name = person.name,
            children = childrenResponse,
            createdDate = person.createdDate.toString(),
            modifiedDate = person.modifiedDate.toString()
        )
    }

    private fun buildChildrenResponse(children: List<Person>): List<PersonResponse> {
        return children.map { person -> PersonResponse(
            id = person.id,
            name = person.name,
            createdDate = person.createdDate.toString(),
            modifiedDate = person.modifiedDate.toString()
        ) }.toList()
    }
}