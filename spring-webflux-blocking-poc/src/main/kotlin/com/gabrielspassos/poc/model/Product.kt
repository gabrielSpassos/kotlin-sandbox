package com.gabrielspassos.poc.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

@RedisHash("product")
data class Product(
    @Id
    var id: String? = null,
    var name: String,
    var price: Double
) : Serializable
