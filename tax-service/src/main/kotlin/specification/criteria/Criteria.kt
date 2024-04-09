package org.gabrielspassos.specification.criteria

import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import java.time.Year

class Criteria(private var criteria: List<SearchTaxCriteria>): SearchTaxCriteria {

    override fun findByCriteria(product: Product, state: State, year: Year): Tax {
        return criteria.stream()
            .map { searchCriteria -> searchCriteria.findByCriteria(product, state, year) }
            .filter { tax -> null != tax }
            .findFirst()
            .orElseThrow { throw RuntimeException("Tax not found") }!!
    }

}