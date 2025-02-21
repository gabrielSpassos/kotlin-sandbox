package com.gabrielspassos.entity

import com.gabrielspassos.annotation.ConvertUUIDToString
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table(name = "person")
data class PersonEntity(

    @Id
    @Column("ID")
    @ConvertUUIDToString
    val id: UUID? = null,

    @ConvertUUIDToString
    @Column("external_id")
    val externalId: UUID,

    @Column("name")
    val name: String,

)