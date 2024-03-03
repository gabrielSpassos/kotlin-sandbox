package org.gabrielspassos.search.builder

import org.gabrielspassos.search.Operator
import org.gabrielspassos.search.criteria.CarEngineCriteria

class EngineCriteriaBuilder(private val builder: SearchCriteriaBuilder) {

    private var operator: Operator? = null
    private var targetPrice: Double? = null

    fun operator(operator: Operator): EngineCriteriaBuilder {
        this.operator = operator
        return this
    }

    fun value(value: Double): EngineCriteriaBuilder {
        this.targetPrice = value
        return this
    }

    fun searchCriteriaBuilder(): SearchCriteriaBuilder {
        this.builder.criteria.addFirst(CarEngineCriteria(operator!!, targetPrice!!))
        return this.builder
    }

}