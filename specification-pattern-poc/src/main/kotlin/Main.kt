package org.gabrielspassos

import org.gabrielspassos.product.Car
import org.gabrielspassos.product.Wheel
import org.gabrielspassos.search.Operator
import org.gabrielspassos.search.SearchCriteria
import org.gabrielspassos.search.builder.SearchCriteriaBuilder

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

    val tireCriteria: SearchCriteria = SearchCriteriaBuilder()
        .withPrice().operator(Operator.GREATER_THAN).value(1500.00).searchCriteriaBuilder()
        .and()
        .withTire().operator(Operator.GREATER_THAN).value(15).searchCriteriaBuilder()
        .build()

    val fullCriteria: SearchCriteria = SearchCriteriaBuilder()
        .withPrice().operator(Operator.GREATER_THAN).value(1500.00).searchCriteriaBuilder()
        .and()
        .withEngine().operator(Operator.LESS_THAN).value(1.8).searchCriteriaBuilder()
        .and()
        .withTire().operator(Operator.GREATER_THAN).value(15).searchCriteriaBuilder()
        .build()

    val car1 = Car(9000.00, 1.0)
    val car2 = Car(10000.00, 1.2)
    val car3 = Car(50000.00, 2.0)

    val wheel1 = Wheel(2000.00, 17)
    val wheel2 = Wheel(1800.00, 15)
    val wheel3 = Wheel(1900.00, 16)

    println("Car 1 satisfies price criteria ${car1.satisfies(simplePriceCriteria)}")
    println("Car 2 satisfies price criteria ${car2.satisfies(simplePriceCriteria)}")
    println("Car 3 satisfies price criteria ${car3.satisfies(simplePriceCriteria)}")
    println()
    println("Car 1 satisfies price AND engine criteria ${car1.satisfies(criteria)}")
    println("Car 2 satisfies price AND engine criteria ${car2.satisfies(criteria)}")
    println("Car 3 satisfies price AND engine criteria ${car3.satisfies(criteria)}")
    println()
    println("Wheel 1 satisfies price AND tire criteria ${wheel1.satisfies(tireCriteria)}")
    println("Wheel 2 satisfies price AND tire criteria ${wheel2.satisfies(tireCriteria)}")
    println("Wheel 3 satisfies price AND tire criteria ${wheel3.satisfies(tireCriteria)}")

    println()
    println("Car 1 satisfies full criteria ${car1.satisfies(fullCriteria)}")
    println("Car 2 satisfies full criteria ${car2.satisfies(fullCriteria)}")
    println("Car 3 satisfies full criteria ${car3.satisfies(fullCriteria)}")
    println("Wheel 1 satisfies full criteria ${wheel1.satisfies(fullCriteria)}")
    println("Wheel 2 satisfies full criteria ${wheel2.satisfies(fullCriteria)}")
    println("Wheel 3 satisfies full criteria ${wheel3.satisfies(fullCriteria)}")

}