package com.gabrielspassos.chain

import com.gabrielspassos.model.Product

class AdminHandler(nextHandler: Handler?): Handler(nextHandler) {

    override fun getProductPrice(product: Product): Double {
        throw IllegalArgumentException("Invalid product")
    }

}