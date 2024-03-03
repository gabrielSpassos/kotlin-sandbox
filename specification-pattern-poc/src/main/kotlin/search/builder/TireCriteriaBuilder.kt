package org.gabrielspassos.search.builder

import org.gabrielspassos.search.Operator
import org.gabrielspassos.search.criteria.WheelTireCriteria


class TireCriteriaBuilder(private val builder: SearchCriteriaBuilder) {

    private var operator: Operator? = null
    private var targetPrice: Int? = null

    fun operator(operator: Operator): TireCriteriaBuilder {
        this.operator = operator
        return this
    }

    fun value(value: Int): TireCriteriaBuilder {
        this.targetPrice = value
        return this
    }

    fun searchCriteriaBuilder(): SearchCriteriaBuilder {
        this.builder.criteria.addFirst(WheelTireCriteria(operator!!, targetPrice!!))
        return this.builder
    }

}