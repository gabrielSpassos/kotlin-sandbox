package org.gabrielspassos.models

import java.time.Year

class Product(val name: String, private val value: Double, private val state: State, private val year: Year) {

    fun calculateFinalPrice(): Double {
        val productTax = calculateProductTax()
        return this.value.plus(productTax)
    }

    private fun calculateProductTax(): Double {
        val yearDifference: Int = if (this.year.isBefore(this.state.taxAliquotBaseYear)) {
            1
        } else {
            this.year.value - this.state.taxAliquotBaseYear.value
        }

        return this.state.baseTaxAliquot.times(yearDifference)
    }

}