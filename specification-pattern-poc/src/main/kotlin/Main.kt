package org.gabrielspassos

import org.gabrielspassos.product.Car
import org.gabrielspassos.search.Operator
import org.gabrielspassos.search.SearchCriteria
import org.gabrielspassos.search.SearchCriteriaBuilder

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val simplePriceCriteria: SearchCriteria = SearchCriteriaBuilder()
        .withPrice().operator(Operator.EQUALS).value(10000.00).searchCriteriaBuilder()
        .build()

    val criteria: SearchCriteria = SearchCriteriaBuilder()
        .withPrice().operator(Operator.GREATER_THAN).value(8000.00).searchCriteriaBuilder()
        .and()
        .withEngine().operator(Operator.LESS_THAN).value(1.8).searchCriteriaBuilder()
        .build()

    val car1 = Car(9000.00, 1.0)
    val car2 = Car(10000.00, 1.2)
    val car3 = Car(50000.00, 2.0)

    println("Car 1 satisfies price criteria ${car1.satisfies(simplePriceCriteria)}")
    println("Car 2 satisfies price criteria ${car2.satisfies(simplePriceCriteria)}")
    println("Car 3 satisfies price criteria ${car3.satisfies(simplePriceCriteria)}")
    println()
    println("Car 1 satisfies price AND engine criteria ${car1.satisfies(criteria)}")
    println("Car 2 satisfies price AND engine criteria ${car2.satisfies(criteria)}")
    println("Car 3 satisfies price AND engine criteria ${car3.satisfies(criteria)}")

}