package com.gabrielspassos.poc.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp
import java.time.Instant.now

@Table(name = "PERSON")
class Person(

    @Id
    @Column("ID")
    val id: String,

    @Column("NAME")
    val name: String,

    @Column("PARENT_ID")
    val parentId: String? = null,

    @Column("CREATED_D")
    val createdDate: Timestamp = Timestamp(now().toEpochMilli()),

    @Column("MODIFIED_D")
    val modifiedDate: Timestamp = Timestamp(now().toEpochMilli()),

)
