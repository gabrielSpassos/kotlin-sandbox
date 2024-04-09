package org.gabrielspassos.chain

import org.gabrielspassos.specification.criteria.Operator
import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import java.time.Year

class ProductTaxByStateAndYearHandler(nextHandler: TaxSearchCriteriaHandler): TaxSearchCriteriaHandler(nextHandler) {

    override fun handleTaxSearch(operator: Operator, product: Product, state: State, year: Year): Tax {
        if (Operator.BY_PRODUCT_STATE_YEAR != operator) {
            return nextHandler!!.handleTaxSearch(operator, product, state, year)
        }

        //todo: do search
        return Tax(2.0)
    }
}