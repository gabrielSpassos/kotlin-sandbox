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
import kotlin.test.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ProductServiceIntegrationTest(@Autowired private val productService: ProductService) {

    companion object {
        @Container
        @ServiceConnection
        @JvmStatic
        val redis: GenericContainer<*>? = GenericContainer(DockerImageName.parse("redis:latest"))
            .withExposedPorts(6379)

        @JvmStatic
        @DynamicPropertySource
        fun redisProperties(registry: DynamicPropertyRegistry) {
            redis?.host?.let { registry.add("spring.redis.host") { it } }
            redis?.firstMappedPort?.let { registry.add("spring.redis.port") { it } }
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

    @Test
    fun shouldSaveOnCacheOnlyOnce() {
        val product = Product(id = UUID.randomUUID().toString(), name = "Product 1", price = 10.0)

        val saveProduct1 = productService.saveProduct(product)
        assertNotNull(saveProduct1)

        val saveProduct2 = productService.saveProduct(product)
        assertNotNull(saveProduct2)

        assertEquals(saveProduct1, saveProduct2)
    }

    @TestFactory
    fun shouldSaveOnCacheOnlyOnceLoadTest(): Collection<DynamicTest> {
        return (1..1000).map { number ->
            DynamicTest.dynamicTest("Save Product on Cache. Test number $number") {
                val product = Product(id = UUID.randomUUID().toString(), name = "Product $number", price = 10.0)

                val saveProduct1 = productService.saveProduct(product)
                assertNotNull(saveProduct1)

                val saveProduct2 = productService.saveProduct(product)
                assertNotNull(saveProduct2)

                assertEquals(saveProduct1, saveProduct2)
            }
        }
    }
}

