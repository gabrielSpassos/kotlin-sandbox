package org.gabrielspassos.search


class SearchCriteriaBuilder {

    private var criteria: List<SearchCriteria> = mutableListOf()
    private var priceCriteriaBuilder: PriceCriteriaBuilder? = null
    private var engineCriteriaBuilder: EngineCriteriaBuilder? = null

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

    fun and(): SearchCriteriaBuilder {
        return this
    }

    fun build(): SearchCriteria {
        return Criteria(criteria)
    }

    class PriceCriteriaBuilder(private val builder: SearchCriteriaBuilder) {

        private var operator: Operator? = null
        private var targetPrice: Double? = null

        fun operator(operator: Operator): PriceCriteriaBuilder {
            this.operator = operator
            return this
        }

        fun value(value: Double): PriceCriteriaBuilder {
            this.targetPrice = value
            return this
        }

        //todo: only for car
        fun searchCriteriaBuilder(): SearchCriteriaBuilder {
            this.builder.criteria.addFirst(CarPriceCriteria(operator!!, targetPrice!!))
            return this.builder
        }

    }

    class EngineCriteriaBuilder(private val builder: SearchCriteriaBuilder) {

        private var operator: Operator? = null
        private var targetPrice: Double? = null

        fun operator(operator: Operator): EngineCriteriaBuilder {
            this.operator = operator
            return this
        }

        fun value(value: Double): EngineCriteriaBuilder {
            this.targetPrice = value
            return this
        }

        //todo: only for car
        fun searchCriteriaBuilder(): SearchCriteriaBuilder {
            this.builder.criteria.addFirst(CarEngineCriteria(operator!!, targetPrice!!))
            return this.builder
        }

    }
}