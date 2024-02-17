package com.gabrielspassos.service

import com.gabrielspassos.dto.PersonDTO
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PersonService(val db: JdbcTemplate) {

    fun findPeople(): List<PersonDTO> {
        return db.query("select * from people") { resultSet, _ ->
            PersonDTO(resultSet.getString("id"), resultSet.getString("name"))
        }
    }

    fun findPersonById(id: String): PersonDTO? {
        return db.queryForObject("select * from people where id = ?", { resultSet, _ ->
            PersonDTO(resultSet.getString("id"), resultSet.getString("name"))
        }, id)
    }

    fun savePerson(person: PersonDTO): PersonDTO? {
        val id = person.id ?: UUID.randomUUID().toString();
        db.update("insert into people values (?, ?)", id, person.name)
        return findPersonById(id)
    }
}