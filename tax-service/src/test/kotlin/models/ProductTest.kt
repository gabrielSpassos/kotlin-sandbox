package models

import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Year

internal class ProductTest {

    @Test
    fun testHello() {
        val state = State("RS", 0.02, Year.of(2020))
        val product = Product("t-shirt", 20.0, state, Year.of(2024))

        assertEquals(20.08, product.calculateFinalPrice())
    }
}