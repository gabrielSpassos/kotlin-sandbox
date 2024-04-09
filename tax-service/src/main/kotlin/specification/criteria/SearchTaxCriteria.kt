package org.gabrielspassos.specification.criteria

import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import java.time.Year

interface SearchTaxCriteria {

    fun findByCriteria(product: Product, state: State, year: Year): Tax?

}