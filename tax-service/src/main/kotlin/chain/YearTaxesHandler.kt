package org.gabrielspassos.chain

import org.gabrielspassos.domain.tax.TaxStorage
import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import org.gabrielspassos.specification.criteria.Operator
import java.time.Year

class YearTaxesHandler(nextHandler: TaxSearchCriteriaHandler): TaxSearchCriteriaHandler(nextHandler) {

    override fun handleTaxSearch(operator: Operator, product: Product?, state: State?, year: Year?): Tax {
        return nextHandler!!.handleTaxSearch(operator, product, state, year)
    }

    override fun handleTaxesSearch(operator: Operator, product: Product?, state: State?, year: Year?): List<Tax> {
        if (Operator.TAXES_BY_YEAR != operator) {
            return nextHandler!!.handleTaxesSearch(operator, product, state, year)
        }

        if (year == null) {
            throw IllegalStateException("year is required for operation $operator")
        }

        val taxStorage = TaxStorage.getInstance()
        return taxStorage.getTaxes().stream()
            .filter {storedTax -> storedTax.years.stream().anyMatch { storedYear -> storedYear == year } }
            .map { storedTax -> storedTax.tax }
            .toList()
    }

}