package search.builder

import org.gabrielspassos.product.Car
import org.gabrielspassos.product.Wheel
import org.gabrielspassos.search.Operator
import org.gabrielspassos.search.SearchCriteria
import org.gabrielspassos.search.builder.SearchCriteriaBuilder
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SearchCriteriaBuilderTest {

    @Test
    fun shouldSearchCarsByPrice() {
        val criteria = SearchCriteriaBuilder()
                .withPrice().operator(Operator.LESS_THAN).value(25000.0).searchCriteriaBuilder()
                .build()

        val car1 = Car(24999.99, 1.0)
        val car2 = Car(25000.0, 1.0)
        val car3 = Car(25000.01, 1.0)

        assertTrue(car1.satisfies(criteria))
        assertFalse(car2.satisfies(criteria))
        assertFalse(car3.satisfies(criteria))
    }

    @Test
    fun shouldSearchWheelByPrice() {
        val criteria = SearchCriteriaBuilder()
            .withPrice().operator(Operator.EQUALS).value(5000.0).searchCriteriaBuilder()
            .build()

        val wheel1 = Wheel(4999.99, 15)
        val wheel2 = Wheel(5000.0, 16)
        val wheel3 = Wheel(5000.01, 17)

        assertFalse(wheel1.satisfies(criteria))
        assertTrue(wheel2.satisfies(criteria))
        assertFalse(wheel3.satisfies(criteria))
    }

    @Test
    fun shouldSearchByPriceAndEngine() {
        val criteria: SearchCriteria = SearchCriteriaBuilder()
            .withPrice().operator(Operator.GREATER_THAN).value(8000.00).searchCriteriaBuilder()
            .and()
            .withEngine().operator(Operator.LESS_THAN).value(1.8).searchCriteriaBuilder()
            .build()

        val car1 = Car(9000.00, 1.0)
        val car2 = Car(10000.00, 1.2)
        val car3 = Car(50000.00, 2.0)

        val wheel1 = Wheel(2000.00, 17)
        val wheel2 = Wheel(1800.00, 15)
        val wheel3 = Wheel(1900.00, 16)

        assertTrue(car1.satisfies(criteria))
        assertTrue(car2.satisfies(criteria))
        assertFalse(car3.satisfies(criteria))

        assertFalse(wheel1.satisfies(criteria))
        assertFalse(wheel2.satisfies(criteria))
        assertFalse(wheel3.satisfies(criteria))
    }

    @Test
    fun shouldSearchByPriceAndTire() {
        val criteria: SearchCriteria = SearchCriteriaBuilder()
            .withPrice().operator(Operator.GREATER_THAN).value(1500.00).searchCriteriaBuilder()
            .and()
            .withTire().operator(Operator.GREATER_THAN).value(15).searchCriteriaBuilder()
            .build()

        val wheel1 = Wheel(2000.00, 17)
        val wheel2 = Wheel(1800.00, 15)
        val wheel3 = Wheel(1400.00, 16)

        assertTrue(wheel1.satisfies(criteria))
        assertFalse(wheel2.satisfies(criteria))
        assertFalse(wheel3.satisfies(criteria))
    }

    @Test
    fun shouldReturnFalseToAllSearch() {
        val criteria: SearchCriteria = SearchCriteriaBuilder()
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

        assertFalse(car1.satisfies(criteria))
        assertFalse(car2.satisfies(criteria))
        assertFalse(car3.satisfies(criteria))

        assertFalse(wheel1.satisfies(criteria))
        assertFalse(wheel2.satisfies(criteria))
        assertFalse(wheel3.satisfies(criteria))
    }
}