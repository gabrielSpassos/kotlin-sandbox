package org.gabrielspassos.domain.tax

import org.gabrielspassos.models.Product
import org.gabrielspassos.models.State
import org.gabrielspassos.models.Tax
import java.time.Year

data class TaxPerStatesProductsYears(val tax: Tax, val states: List<State>, val products: List<Product>, val years: List<Year>)
