package org.gabrielspassos.product

import org.gabrielspassos.search.SearchCriteria

open class Product {

    fun satisfies(criteria: SearchCriteria): Boolean {
        return criteria.isSatisfiedBy(this)
    }

}