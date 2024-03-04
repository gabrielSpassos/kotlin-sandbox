package org.gabrielspassos.search

import org.gabrielspassos.product.Product

class Criteria(private var criteria: List<SearchCriteria>): SearchCriteria {

    override fun isSatisfiedBy(product: Product): Boolean {
        return criteria.stream().allMatch { searchCriteria -> searchCriteria.isSatisfiedBy(product) }
    }

}