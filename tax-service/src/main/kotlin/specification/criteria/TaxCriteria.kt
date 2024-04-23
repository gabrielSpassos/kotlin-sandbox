package org.gabrielspassos.specification.criteria

import org.gabrielspassos.chain.factory.ChainFactory
import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import java.time.Year

class TaxCriteria(private val operator: Operator,
                  private val product: Product?,
                  private val state: State?,
                  private val year: Year?): SearchTaxCriteria {

    override fun findByCriteria(): Tax {
        val chainFactory = ChainFactory()
        val taxSearchCriteriaChain = chainFactory.createTaxSearchCriteriaChain()

        return taxSearchCriteriaChain.handleTaxSearch(operator, product, state, year)
    }

}