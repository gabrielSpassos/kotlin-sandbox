package com.gabrielspassos.factory

import com.gabrielspassos.chain.AdminHandler
import com.gabrielspassos.chain.Handler
import com.gabrielspassos.chain.ManagerHandler
import com.gabrielspassos.chain.SalesmanHandler

class ChainFactory {

    fun createChain(): Handler {
        val adminHandler = AdminHandler(null)
        val managerHandler = ManagerHandler(adminHandler)
        val salesmanHandler = SalesmanHandler(managerHandler)

        return salesmanHandler
    }
}