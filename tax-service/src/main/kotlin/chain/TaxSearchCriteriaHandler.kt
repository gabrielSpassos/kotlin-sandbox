package org.gabrielspassos.chain

import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import org.gabrielspassos.specification.criteria.Operator
import java.time.Year

abstract class TaxSearchCriteriaHandler(val nextHandler: TaxSearchCriteriaHandler?) {

    abstract fun handleTaxSearch(operator: Operator, product: Product?, state: State?, year: Year?): Tax

    abstract fun handleTaxesSearch(operator: Operator, product: Product?, state: State?, year: Year?): List<Tax>
}