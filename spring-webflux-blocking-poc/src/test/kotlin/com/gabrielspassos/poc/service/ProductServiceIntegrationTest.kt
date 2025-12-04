package com.gabrielspassos.poc.service

import com.gabrielspassos.poc.model.Product
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceIntegrationTest(@Autowired private val productService: ProductService) {

    companion object {
        private val redis = GenericContainer(DockerImageName.parse("redis:latest"))
            .withExposedPorts(6379)

        init {
            redis.start()
            System.setProperty("spring.redis.host", redis.host)
            System.setProperty("spring.redis.port", redis.getMappedPort(6379).toString())
        }
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

