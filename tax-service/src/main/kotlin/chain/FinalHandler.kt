package org.gabrielspassos.chain

import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import org.gabrielspassos.specification.criteria.Operator
import java.time.Year

class FinalHandler : TaxSearchCriteriaHandler(null) {

    override fun handleTaxSearch(operator: Operator, product: Product?, state: State?, year: Year?): Tax {
        throw IllegalStateException("not found valid handler for operation $operator")
    }

}