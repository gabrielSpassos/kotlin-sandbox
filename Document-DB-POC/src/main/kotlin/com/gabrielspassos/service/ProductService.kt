package com.gabrielspassos.service

import com.gabrielspassos.controller.v1.request.ProductRequest
import com.gabrielspassos.dao.ProductDao
import com.gabrielspassos.entity.ProductEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class ProductService @Autowired constructor(private val productDao: ProductDao) {

    fun save(productRequest: ProductRequest): ProductEntity {
        val productEntity = ProductEntity(
            id = null,
            name = productRequest.name,
            category = productRequest.category,
            value = productRequest.value,
            isActive = productRequest.isActive
        )
        return productDao.save(productEntity)
    }

    fun findByName(name: String): Optional<ProductEntity> {
        return productDao.findByName(name)
    }

    fun findAll(): List<ProductEntity> {
        return productDao.findAll()
    }
}