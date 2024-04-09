package org.gabrielspassos.chain.factory

import org.gabrielspassos.chain.FinalHandler
import org.gabrielspassos.chain.LatestProductTaxByStateHandler
import org.gabrielspassos.chain.ProductTaxByStateAndYearHandler
import org.gabrielspassos.chain.TaxSearchCriteriaHandler

class ChainFactory {

    fun createTaxSearchCriteriaChain(): TaxSearchCriteriaHandler {
        val finalHandler = FinalHandler()
        val productTaxByStateHandler = ProductTaxByStateAndYearHandler(finalHandler)
        val latestProductTaxByStateHandler = LatestProductTaxByStateHandler(productTaxByStateHandler)

        return latestProductTaxByStateHandler
    }

}