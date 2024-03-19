package com.gabrielspassos.chain

import com.gabrielspassos.model.Product

abstract class Handler(var nextHandler: Handler?) {

    abstract fun getProductPrice(product: Product): Double
}