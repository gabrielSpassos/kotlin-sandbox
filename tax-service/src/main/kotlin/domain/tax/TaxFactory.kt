package org.gabrielspassos.domain.tax

import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import java.time.Year

class TaxFactory private constructor() {
    companion object {

        @Volatile
        private var instance: TaxFactory? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: TaxFactory().also { instance = it }
            }
    }

    private var taxes: MutableList<TaxPerStatesProductsYears> = mutableListOf()

    init {
        val year22 = Year.of(2022)
        val year23 = Year.of(2023)
        val year24 = Year.of(2024)

        val product1 = Product("Product 1", 10.0)
        val product2 = Product("Product 2", 14.0)

        val state1 = State("State 1")
        val state2 = State("State 2")

        createTaxStorage(1.0, listOf(state1), listOf(product1, product2), listOf(year22))
        createTaxStorage(2.5, listOf(state1), listOf(product1), listOf(year23, year24))
        createTaxStorage(3.5, listOf(state2), listOf(product2), listOf(year23))
        createTaxStorage(4.5, listOf(state2), listOf(product2), listOf(year24))
        createTaxStorage(5.5, listOf(state2), listOf(product1), listOf(year23))
    }

    fun getTaxes() = taxes

    private fun createTaxStorage(taxValue: Double, states: List<State>, products: List<Product>, years: List<Year>): TaxPerStatesProductsYears {
        val tax = Tax(taxValue)

        val taxPerStatesProductsYears = TaxPerStatesProductsYears(tax, states, products, years)

        addTax(taxPerStatesProductsYears)

        return taxPerStatesProductsYears
    }

    private fun addTax(taxPerStatesProductsYears: TaxPerStatesProductsYears) {
        taxes.add(taxPerStatesProductsYears)
    }
}