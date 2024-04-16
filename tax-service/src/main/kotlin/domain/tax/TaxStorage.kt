package org.gabrielspassos.domain.tax

class TaxStorage private constructor() {
    companion object {

        @Volatile
        private var instance: TaxStorage? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: TaxStorage().also { instance = it }
            }
    }

    private var taxes: MutableList<TaxPerStatesProductsYears> = mutableListOf()

    fun addTax(taxPerStatesProductsYears: TaxPerStatesProductsYears) {
        taxes.add(taxPerStatesProductsYears)
    }

    fun getTaxes() = taxes
}