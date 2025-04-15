package com.gabrielspassos.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.math.BigDecimal
import java.util.UUID

@Document(collection = "products")
data class ProductEntity(
    @Id
    val id: UUID? = null,

    @Indexed(unique = true)
    val name: String? = null,

    val category: String? = null,

    @Field("price")
    val value: BigDecimal? = null,

    val isActive: Boolean = false
)
