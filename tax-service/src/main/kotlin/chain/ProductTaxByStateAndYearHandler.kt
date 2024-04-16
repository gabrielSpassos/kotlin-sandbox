package org.gabrielspassos.chain

import org.gabrielspassos.domain.tax.TaxPerStatesProductsYears
import org.gabrielspassos.domain.tax.TaxStorage
import org.gabrielspassos.specification.criteria.Operator
import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import java.time.Year
import java.util.function.Predicate

class ProductTaxByStateAndYearHandler(nextHandler: TaxSearchCriteriaHandler): TaxSearchCriteriaHandler(nextHandler) {

    override fun handleTaxSearch(operator: Operator, product: Product, state: State, year: Year): Tax {
        if (Operator.BY_PRODUCT_STATE_YEAR != operator) {
            return nextHandler!!.handleTaxSearch(operator, product, state, year)
        }

        val taxStorage = TaxStorage.getInstance()
        return taxStorage.getTaxes().stream()
            .filter(isSameProduct(product).and(isSameState(state)).and(isSameYear(year)))
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

    private fun isSameYear(year: Year): Predicate<TaxPerStatesProductsYears> {
        return Predicate {
                storedTax -> storedTax.years.stream()
            .anyMatch { storedYear -> storedYear == year}
        }
    }
}