package com.gabrielspassos.repository

import com.gabrielspassos.entity.UserEntity
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.UUID

@Repository
interface UserRepository : CrudRepository<UserEntity, UUID> {

    @Query("""
        SELECT * FROM users 
        WHERE status = :status 
        and updated_at < :thresholdDate
        """)
    fun findByStatusAndUpdatedAtOlderThan(status: String, thresholdDate: LocalDateTime): List<UserEntity>

}