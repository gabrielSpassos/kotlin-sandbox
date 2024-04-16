package org.gabrielspassos.domain.tax

import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import java.time.Year

class TaxFactory {

    fun createTaxStorage(taxValue: Double, states: List<State>, products: List<Product>, years: List<Year>): TaxPerStatesProductsYears {
        val tax = Tax(taxValue)

        val taxPerStatesProductsYears = TaxPerStatesProductsYears(tax, states, products, years)

        TaxStorage.getInstance().addTax(taxPerStatesProductsYears)

        return taxPerStatesProductsYears
    }

}