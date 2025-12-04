package com.gabrielspassos.poc.repository

import com.gabrielspassos.poc.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: CrudRepository<Product, String> {
}