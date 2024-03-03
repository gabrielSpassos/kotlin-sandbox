package org.gabrielspassos.search.builder

import org.gabrielspassos.search.Operator
import org.gabrielspassos.search.criteria.PriceCriteria

class PriceCriteriaBuilder(private val builder: SearchCriteriaBuilder) {

    private var operator: Operator? = null
    private var targetPrice: Double? = null

    fun operator(operator: Operator): PriceCriteriaBuilder {
        this.operator = operator
        return this
    }

    fun value(value: Double): PriceCriteriaBuilder {
        this.targetPrice = value
        return this
    }

    fun searchCriteriaBuilder(): SearchCriteriaBuilder {
        this.builder.criteria.addFirst(PriceCriteria(operator!!, targetPrice!!))
        return this.builder
    }

}