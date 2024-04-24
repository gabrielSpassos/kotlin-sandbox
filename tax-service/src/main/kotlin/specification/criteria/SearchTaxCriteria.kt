package org.gabrielspassos.specification.criteria

import org.gabrielspassos.models.Tax

interface SearchTaxCriteria {

    fun findTaxByCriteria(): Tax?

    fun findTaxesByCriteria(): List<Tax>

}