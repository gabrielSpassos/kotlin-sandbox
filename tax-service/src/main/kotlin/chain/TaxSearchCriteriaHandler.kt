package org.gabrielspassos.chain

import org.gabrielspassos.specification.criteria.Operator
import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import java.time.Year

abstract class TaxSearchCriteriaHandler(val nextHandler: TaxSearchCriteriaHandler?) {

    abstract fun handleTaxSearch(operator: Operator, product: Product, state: State, year: Year): Tax

}