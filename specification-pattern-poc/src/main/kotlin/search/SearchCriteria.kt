package org.gabrielspassos.search

import org.gabrielspassos.product.Product

interface SearchCriteria {

    fun isSatisfiedBy(product: Product): Boolean
}