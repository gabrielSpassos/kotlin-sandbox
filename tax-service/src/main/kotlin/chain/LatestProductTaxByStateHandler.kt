package org.gabrielspassos.chain

import org.gabrielspassos.specification.criteria.Operator
import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import java.time.Year

class LatestProductTaxByStateHandler(nextHandler: TaxSearchCriteriaHandler): TaxSearchCriteriaHandler(nextHandler) {

    override fun handleTaxSearch(operator: Operator, product: Product, state: State, year: Year): Tax {
        if (Operator.LATEST_PRODUCT_BY_STATE != operator) {
            return nextHandler!!.handleTaxSearch(operator, product, state, year)
        }

        //todo: execute search
        return Tax(1.0)
    }
}