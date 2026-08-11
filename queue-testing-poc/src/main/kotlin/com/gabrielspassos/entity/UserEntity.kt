package com.gabrielspassos.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.UUID

@Table(name = "users")
class UserEntity(

    @Id
    @Column("id")
    val id: UUID? = null,

    @Column("name")
    val name: String,

    @Column("status")
    val status: String,

    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column("updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)