package org.gabrielspassos.specification.criteria

import org.gabrielspassos.models.Tax

class Criteria(private var criteria: List<SearchTaxCriteria>): SearchTaxCriteria {

    override fun findByCriteria(): Tax {
        return criteria.stream()
            .map { searchCriteria -> searchCriteria.findByCriteria() }
            .filter { tax -> null != tax }
            .map { tax -> tax!! }
            .findFirst()
            .orElseThrow { throw RuntimeException("Tax not found") }!!
    }

}