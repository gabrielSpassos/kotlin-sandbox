package com.gabrielspassos.repository

import com.gabrielspassos.entity.PersonEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface PersonRepository : CrudRepository<PersonEntity, UUID>  {

    fun findByExternalId(externalId: UUID): Optional<PersonEntity>

}