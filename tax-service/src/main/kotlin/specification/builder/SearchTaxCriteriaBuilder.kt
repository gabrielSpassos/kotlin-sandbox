package org.gabrielspassos.specification.builder

import org.gabrielspassos.specification.criteria.Criteria
import org.gabrielspassos.specification.criteria.SearchTaxCriteria

class SearchTaxCriteriaBuilder {

    val criteria: List<SearchTaxCriteria> = mutableListOf()
    private var taxCriteriaBuilder: TaxCriteriaBuilder? = null

    fun withTax(): TaxCriteriaBuilder {
        if (taxCriteriaBuilder == null) {
            taxCriteriaBuilder = TaxCriteriaBuilder(this)
        }

        return taxCriteriaBuilder as TaxCriteriaBuilder
    }

    fun and(): SearchTaxCriteriaBuilder {
        return this
    }

    fun build(): SearchTaxCriteria {
        return Criteria(criteria)
    }
}