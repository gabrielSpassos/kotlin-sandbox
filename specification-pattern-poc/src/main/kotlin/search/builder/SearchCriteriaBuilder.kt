package org.gabrielspassos.search.builder

import org.gabrielspassos.search.Criteria
import org.gabrielspassos.search.SearchCriteria


class SearchCriteriaBuilder {

    var criteria: List<SearchCriteria> = mutableListOf()
    private var priceCriteriaBuilder: PriceCriteriaBuilder? = null
    private var engineCriteriaBuilder: EngineCriteriaBuilder? = null
    private var tireCriteriaBuilder: TireCriteriaBuilder? = null

    fun withPrice(): PriceCriteriaBuilder {
        if (priceCriteriaBuilder == null) {
            priceCriteriaBuilder = PriceCriteriaBuilder(this)
        }

        return priceCriteriaBuilder as PriceCriteriaBuilder
    }

    fun withEngine(): EngineCriteriaBuilder {
        if (engineCriteriaBuilder == null) {
            engineCriteriaBuilder = EngineCriteriaBuilder(this)
        }

        return engineCriteriaBuilder as EngineCriteriaBuilder
    }

    fun withTire(): TireCriteriaBuilder {
        if (tireCriteriaBuilder == null) {
            tireCriteriaBuilder = TireCriteriaBuilder(this)
        }

        return tireCriteriaBuilder as TireCriteriaBuilder
    }

    fun and(): SearchCriteriaBuilder {
        return this
    }

    fun build(): SearchCriteria {
        return Criteria(criteria)
    }

}