package com.gabrielspassos.dao

import com.gabrielspassos.dao.repository.ProductRepository
import com.gabrielspassos.entity.ProductEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class ProductDao (@Autowired private val productRepository: ProductRepository) {
    
    fun findByName(name: String): Optional<ProductEntity> {
        return Optional.ofNullable(productRepository.findByName(name))
    }

    fun save(product: ProductEntity): ProductEntity {
        return productRepository.save(product)
    }

    fun findAll(): List<ProductEntity> {
        return productRepository.findAll()
    }
}