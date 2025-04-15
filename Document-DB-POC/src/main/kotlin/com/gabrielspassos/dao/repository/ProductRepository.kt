package com.gabrielspassos.dao.repository

import com.gabrielspassos.entity.ProductEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductRepository: MongoRepository<ProductEntity, UUID> {
    
    fun findByName(name: String): ProductEntity?
    
}