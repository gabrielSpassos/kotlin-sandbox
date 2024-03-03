package org.gabrielspassos.search.criteria

import org.gabrielspassos.product.Car
import org.gabrielspassos.product.Product
import org.gabrielspassos.search.Operator
import org.gabrielspassos.search.SearchCriteria

class CarEngineCriteria(private val operator: Operator, private val target: Double): SearchCriteria {

    override fun isSatisfiedBy(product: Product): Boolean {
        if (product !is Car) {
            return false
        }

        return when (this.operator) {
            Operator.LESS_THAN -> product.engine < target
            Operator.EQUALS -> target == product.engine
            Operator.GREATER_THAN -> product.engine > target
        }
    }

}