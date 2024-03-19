package com.gabrielspassos.chain

import com.gabrielspassos.model.ComplexProduct
import com.gabrielspassos.model.Product

class ManagerHandler(nextHandler: Handler): Handler(nextHandler) {

    override fun getProductPrice(product: Product): Double {
        if (product !is ComplexProduct) {
            return nextHandler!!.getProductPrice(product)
        }

        val finalAmount = product.price * product.quantity
        return finalAmount + (finalAmount * 0.02)
    }

}