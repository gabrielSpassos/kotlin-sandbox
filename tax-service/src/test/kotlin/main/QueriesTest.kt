package main

import org.gabrielspassos.domain.tax.TaxFactory
import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.specification.builder.SearchTaxCriteriaBuilder
import org.gabrielspassos.specification.criteria.Operator
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import java.time.Year
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class QueriesTest {

    @Test
    fun shouldFetchTaxByProductAndStateAndYear() {
        // given
        val year23 = Year.of(2023)
        val year24 = Year.of(2024)

        val product1 = Product("Product 1", 10.0)
        val product2 = Product("Product 2", 14.0)

        val state1 = State("State 1")
        val state2 = State("State 2")

        val taxFactory = TaxFactory()
        taxFactory.createTaxStorage(2.5, listOf(state1), listOf(product1), listOf(year23, year24))
        taxFactory.createTaxStorage(3.5, listOf(state2), listOf(product2), listOf(year23)) // this is the one
        taxFactory.createTaxStorage(4.5, listOf(state2), listOf(product2), listOf(year24))
        taxFactory.createTaxStorage(5.5, listOf(state2), listOf(product1), listOf(year23))

        // when
        val search = SearchTaxCriteriaBuilder()
            .withTax()
            .operator(Operator.BY_PRODUCT_STATE_YEAR)
            .state(state2)
            .product(product2)
            .year(year23)
            .searchTaxCriteriaBuilder()
            .build()

        val tax = search.findTaxByCriteria()

        // then
        assertNotNull(tax)
        assertEquals(3.5, tax.value)
    }

    @Test
    fun shouldFetchLatestTaxByProductAndState() {
        // given
        val year23 = Year.of(2023)
        val year24 = Year.of(2024)

        val product1 = Product("Product 1", 10.0)
        val product2 = Product("Product 2", 14.0)

        val state1 = State("State 1")
        val state2 = State("State 2")

        // todo: send this on the factory
        val taxFactory = TaxFactory()
        taxFactory.createTaxStorage(2.5, listOf(state1), listOf(product1), listOf(year23, year24))
        taxFactory.createTaxStorage(3.5, listOf(state2), listOf(product2), listOf(year23))
        taxFactory.createTaxStorage(4.5, listOf(state2), listOf(product2), listOf(year24))
        taxFactory.createTaxStorage(5.5, listOf(state2), listOf(product1), listOf(year23))

        // when
        val search = SearchTaxCriteriaBuilder()
            .withTax()
            .operator(Operator.LATEST_PRODUCT_BY_STATE)
            .state(state2)
            .product(product2)
            .searchTaxCriteriaBuilder()
            .build()

        val tax = search.findTaxByCriteria()

        // then
        assertNotNull(tax)
        assertEquals(4.5, tax.value)
    }

    @Test
    fun shouldFetchTaxesByYear() {
        // given
        val year23 = Year.of(2023)
        val year24 = Year.of(2024)

        val product1 = Product("Product 1", 10.0)
        val product2 = Product("Product 2", 14.0)

        val state1 = State("State 1")
        val state2 = State("State 2")

        val taxFactory = TaxFactory()
        taxFactory.createTaxStorage(2.5, listOf(state1), listOf(product1), listOf(year23, year24))
        taxFactory.createTaxStorage(3.5, listOf(state2), listOf(product2), listOf(year23)) // this is the one
        taxFactory.createTaxStorage(4.5, listOf(state2), listOf(product2), listOf(year24))
        taxFactory.createTaxStorage(5.5, listOf(state2), listOf(product1), listOf(year23))

        // when
        val search = SearchTaxCriteriaBuilder()
            .withTax()
            .operator(Operator.TAXES_BY_YEAR)
            .year(year23)
            .searchTaxCriteriaBuilder()
            .build()

        val taxes = search.findTaxesByCriteria()

        // then
        assertNotNull(taxes)
        assertFalse { taxes.isEmpty() }
        assertEquals(3, taxes.size)
        assertEquals(2.5, taxes[0].value)
        assertEquals(3.5, taxes[1].value)
        assertEquals(5.5, taxes[2].value)
    }
}