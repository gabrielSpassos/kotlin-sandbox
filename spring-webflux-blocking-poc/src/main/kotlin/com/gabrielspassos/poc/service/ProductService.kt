package com.gabrielspassos.poc.service

import com.gabrielspassos.poc.model.Product
import com.gabrielspassos.poc.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(
    @Autowired private val productRepository: ProductRepository,
) {

    fun saveProduct(product: Product): Product {
        return productRepository.save(product)
    }

    fun getProductById(id: String): Product {
        return productRepository.findById(id).orElseThrow { RuntimeException("Product not found") }
    }

}