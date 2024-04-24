package org.gabrielspassos.specification.criteria

import org.gabrielspassos.models.Tax

class Criteria(private var taxCriteria: List<SearchTaxCriteria>): SearchTaxCriteria {

    override fun findTaxByCriteria(): Tax {
        return taxCriteria.stream()
            .map { searchCriteria -> searchCriteria.findTaxByCriteria() }
            .filter { tax -> null != tax }
            .map { tax -> tax!! }
            .findFirst()
            .orElseThrow { throw RuntimeException("Tax not found") }!!
    }

    override fun findTaxesByCriteria(): List<Tax> {
        return taxCriteria.stream()
            .map { searchCriteria -> searchCriteria.findTaxesByCriteria() }
            .flatMap { taxes -> taxes.stream() }
            .toList()
    }

}