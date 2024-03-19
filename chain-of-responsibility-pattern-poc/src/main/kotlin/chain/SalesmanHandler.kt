package com.gabrielspassos.chain

import com.gabrielspassos.model.Product
import com.gabrielspassos.model.SimpleProduct

class SalesmanHandler(nextHandler: Handler): Handler(nextHandler) {

    override fun getProductPrice(product: Product): Double {
        if (product !is SimpleProduct) {
            return nextHandler!!.getProductPrice(product)
        }

        return product.price + (product.price * 0.01)
    }

}