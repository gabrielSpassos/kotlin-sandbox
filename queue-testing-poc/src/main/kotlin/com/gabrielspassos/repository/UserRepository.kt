package com.gabrielspassos.repository

import com.gabrielspassos.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : CrudRepository<UserEntity, UUID> {

    fun findByStatus(status: String): List<UserEntity>

}