package com.gabrielspassos.poc.service

import com.gabrielspassos.poc.model.Product
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ProductServiceIntegrationTest(@Autowired private val productService: ProductService) {

    companion object {
        @Container
        @ServiceConnection
        @JvmStatic
        val redis = GenericContainer(DockerImageName.parse("redis:latest")).withExposedPorts(6379)
    }

    @Test
    fun shouldFindOnCacheById() {
        val product = Product(id = UUID.randomUUID().toString(), name = "Product 1", price = 10.0)

        val saveProduct = productService.saveProduct(product)
        assertNotNull(saveProduct)

        saveProduct.id?.let {
            val cachedProduct = productService.getProductById(it)
            assert(cachedProduct == saveProduct)
        }

    }
}

