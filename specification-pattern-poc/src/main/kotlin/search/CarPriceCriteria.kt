package org.gabrielspassos.search

import org.gabrielspassos.product.Car
import org.gabrielspassos.product.Product

class CarPriceCriteria(private val operator: Operator, private val target: Double): SearchCriteria {

    override fun isSatisfiedBy(product: Product): Boolean {
        if (product !is Car) {
            return false
        }

        return when (this.operator) {
            Operator.LESS_THAN -> product.price < target
            Operator.EQUALS -> target == product.price
            Operator.GREATER_THAN -> product.price > target
        }
    }

}