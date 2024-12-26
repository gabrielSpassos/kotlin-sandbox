package com.gabrielspassos

import com.gabrielspassos.dao.BankDAO
import com.gabrielspassos.repository.inmemory.InMemoryBankRepository
import com.gabrielspassos.service.BankService


fun main() {
    try {
        println("Bank Basic POC")
        val bankRepository = InMemoryBankRepository()
        val bankDAO = BankDAO(bankRepository = bankRepository)
        val bankService = BankService(bankDAO = bankDAO)

        bankService.seed()

        bankService.findById(1L).map {
            println(it)
        }

        bankService.findById(2L).map {
            println(it)
        }

        bankService.findById(3L).map {
            println(it)
        }

        bankService.findById(4L).map {
            println(it)
        }
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}