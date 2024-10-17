package com.gabrielspassos.poc.domain.repository

import com.gabrielspassos.poc.domain.model.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : CrudRepository<Person, String> {

    fun findByParentId(parentId: String): List<Person>

}