package org.gabrielspassos.search.criteria

import org.gabrielspassos.product.Car
import org.gabrielspassos.product.Product
import org.gabrielspassos.product.Wheel
import org.gabrielspassos.search.Operator
import org.gabrielspassos.search.SearchCriteria

class PriceCriteria(private val operator: Operator, private val target: Double): SearchCriteria {

    override fun isSatisfiedBy(product: Product): Boolean {
        if (product is Car) {
            return when (this.operator) {
                Operator.LESS_THAN -> product.price < target
                Operator.EQUALS -> target == product.price
                Operator.GREATER_THAN -> product.price > target
            }
        }

        if (product is Wheel) {
            return when (this.operator) {
                Operator.LESS_THAN -> product.price < target
                Operator.EQUALS -> target == product.price
                Operator.GREATER_THAN -> product.price > target
            }
        }

        return false
    }

}