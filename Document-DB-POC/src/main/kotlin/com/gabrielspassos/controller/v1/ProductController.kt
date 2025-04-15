package com.gabrielspassos.controller.v1

import com.gabrielspassos.controller.v1.request.ProductRequest
import com.gabrielspassos.entity.ProductEntity
import com.gabrielspassos.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/products")
class ProductController @Autowired constructor(private val productService: ProductService) {

    @PostMapping("")
    fun createProduct(@RequestBody productRequest: ProductRequest): ResponseEntity<ProductEntity> {
        val savedProductEntity = productService.save(productRequest)
        return ResponseEntity.ok().body(savedProductEntity)
    }

    @GetMapping("/product/{name}")
    fun findByName(@PathVariable name: String): ResponseEntity<ProductEntity> {
        val productEntity = productService.findByName(name)
        return productEntity.map { ResponseEntity.ok().body(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<ProductEntity>> {
        val productEntities = productService.findAll()
        return ResponseEntity.ok().body(productEntities)
    }
}