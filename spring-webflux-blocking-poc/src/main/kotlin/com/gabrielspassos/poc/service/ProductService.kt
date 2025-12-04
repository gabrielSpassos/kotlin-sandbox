package com.gabrielspassos.poc.service

import com.gabrielspassos.poc.model.Product
import com.gabrielspassos.poc.repository.ProductRepository
import io.lettuce.core.api.StatefulRedisConnection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val redisConnection: StatefulRedisConnection<String, String>,
) {

    fun saveProduct(product: Product): Product {
        if (null == product.id) {
            print("Invalid product id as null")
            throw IllegalStateException("invalid product id")
        }

        val productIdOnCache = redisConnection.sync().get(product.id)
        if (null == productIdOnCache) {
            print("product id is not on cache yet, creating product")
            val savedProduct = productRepository.save(product)
            redisConnection.sync().set(product.id, product.id)
            return savedProduct
        } else {
            print("product id is already on cache, getting product from cache")
            return product.id?.let { getProductById(it) } ?: throw IllegalStateException("invalid product id")
        }
    }

    fun getProductById(id: String): Product {
        return productRepository.findById(id).orElseThrow { RuntimeException("Product not found") }
    }

}