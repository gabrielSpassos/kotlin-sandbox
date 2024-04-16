package org.gabrielspassos.chain

import org.gabrielspassos.domain.tax.TaxPerStatesProductsYears
import org.gabrielspassos.domain.tax.TaxStorage
import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import org.gabrielspassos.specification.criteria.Operator
import java.time.Year
import java.util.function.Predicate

class LatestProductTaxByStateHandler(nextHandler: TaxSearchCriteriaHandler): TaxSearchCriteriaHandler(nextHandler) {

    override fun handleTaxSearch(operator: Operator, product: Product, state: State, year: Year): Tax {
        if (Operator.LATEST_PRODUCT_BY_STATE != operator) {
            return nextHandler!!.handleTaxSearch(operator, product, state, year)
        }

        val taxStorage = TaxStorage.getInstance()
        return taxStorage.getTaxes().stream()
            .filter(isSameProduct(product).and(isSameState(state)))
            .sorted(compareYears().reversed())
            .map { storedTax -> storedTax.tax }
            .findFirst()
            .orElse(null)
    }

    private fun isSameProduct(product: Product): Predicate<TaxPerStatesProductsYears> {
        return Predicate {
                storedTax -> storedTax.products.stream()
            .anyMatch { storedProduct -> storedProduct.name == product.name }
        }
    }

    private fun isSameState(state: State): Predicate<TaxPerStatesProductsYears> {
        return Predicate {
                storedTax -> storedTax.states.stream()
            .anyMatch { storedState -> storedState.name == state.name }
        }
    }

    private fun compareYears(): Comparator<TaxPerStatesProductsYears> {
        return Comparator { o1, o2 ->
            val latestYearOfO1 = getLatestYear(o1)
            val latestYearOfO2 = getLatestYear(o2)
            return@Comparator latestYearOfO1.compareTo(latestYearOfO2)
        }
    }

    private fun getLatestYear(taxPerStatesProductsYears: TaxPerStatesProductsYears): Year {
        return taxPerStatesProductsYears.years.maxOf { it }
    }
}