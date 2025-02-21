package com.gabrielspassos.entity

import com.gabrielspassos.annotation.ConvertUUIDToString
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table(name = "person")
class PersonEntity(

    @Id
    @Column("ID")
    val id: UUID? = null,

    @ConvertUUIDToString
    @Column("external_id")
    val externalId: UUID,

    @Column("name")
    val name: String,

) {
    fun copy(id: UUID? = this.id, externalId: UUID = this.externalId, name: String = this.name) =
        PersonEntity(id = id, externalId = externalId, name = name)
}