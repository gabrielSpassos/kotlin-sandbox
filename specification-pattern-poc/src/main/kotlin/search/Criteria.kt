package org.gabrielspassos.search

import org.gabrielspassos.product.Product

class Criteria(private var criteria: List<SearchCriteria>): SearchCriteria {

    override fun isSatisfiedBy(product: Product): Boolean {

        for (searchCriteria in criteria) {
            if (!searchCriteria.isSatisfiedBy(product)) {
                return false
            }
        }

        return true
    }

}