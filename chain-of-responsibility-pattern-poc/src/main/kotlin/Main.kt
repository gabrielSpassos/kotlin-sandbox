package com.gabrielspassos

import com.gabrielspassos.factory.ChainFactory
import com.gabrielspassos.model.ComplexProduct
import com.gabrielspassos.model.InvalidProduct
import com.gabrielspassos.model.SimpleProduct
import java.util.*


fun main() {

    val simpleProduct = SimpleProduct(UUID.randomUUID().toString(), 2.5)
    val complexProduct = ComplexProduct(UUID.randomUUID().toString(), 6.3, 2)
    val invalidProduct = InvalidProduct()

    val products = listOf(simpleProduct, complexProduct, invalidProduct)

    val chainFactory = ChainFactory()
    val handler = chainFactory.createChain()

    for (product in products) {
        try {
            val productPrice = handler.getProductPrice(product)
            println("Product price $productPrice")
        } catch (e: Exception) {
            println("Failed to get product price. Error $e")
        }
    }
}