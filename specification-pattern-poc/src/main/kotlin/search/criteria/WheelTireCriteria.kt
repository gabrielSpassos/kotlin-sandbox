package org.gabrielspassos.search.criteria

import org.gabrielspassos.product.Product
import org.gabrielspassos.product.Wheel
import org.gabrielspassos.search.Operator
import org.gabrielspassos.search.SearchCriteria

class WheelTireCriteria(private val operator: Operator, private val target: Int): SearchCriteria {

    override fun isSatisfiedBy(product: Product): Boolean {
        if (product !is Wheel) {
            return false
        }

        return when (this.operator) {
            Operator.LESS_THAN -> product.tire < target
            Operator.EQUALS -> target == product.tire
            Operator.GREATER_THAN -> product.tire > target
        }
    }

}