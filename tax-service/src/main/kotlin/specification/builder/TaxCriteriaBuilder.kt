package org.gabrielspassos.specification.builder

import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.specification.criteria.Operator
import org.gabrielspassos.specification.criteria.TaxCriteria
import java.time.Year

class TaxCriteriaBuilder(private val builder: SearchTaxCriteriaBuilder) {

    private var operator: Operator? = null
    private var product: Product? = null
    private var state: State? = null
    private var year: Year? = null

    fun operator(operator: Operator): TaxCriteriaBuilder {
        this.operator = operator
        return this
    }

    fun product(product: Product): TaxCriteriaBuilder {
        this.product = product
        return this
    }

    fun state(state: State): TaxCriteriaBuilder {
        this.state = state
        return this
    }

    fun year(year: Year): TaxCriteriaBuilder {
        this.year = year
        return this
    }

    fun searchTaxCriteriaBuilder(): SearchTaxCriteriaBuilder {
        this.builder.criteria.add(TaxCriteria(operator!!))
        return this.builder
    }

}