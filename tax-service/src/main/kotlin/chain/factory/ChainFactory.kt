package org.gabrielspassos.chain.factory

import org.gabrielspassos.chain.FinalHandler
import org.gabrielspassos.chain.LatestProductTaxByStateHandler
import org.gabrielspassos.chain.ProductTaxByStateAndYearHandler
import org.gabrielspassos.chain.TaxSearchCriteriaHandler
import org.gabrielspassos.chain.YearTaxesHandler

class ChainFactory {

    fun createTaxSearchCriteriaChain(): TaxSearchCriteriaHandler {
        val finalHandler = FinalHandler()
        val yearTaxesHandler = YearTaxesHandler(finalHandler)
        val productTaxByStateHandler = ProductTaxByStateAndYearHandler(yearTaxesHandler)
        val latestProductTaxByStateHandler = LatestProductTaxByStateHandler(productTaxByStateHandler)

        return latestProductTaxByStateHandler
    }

}