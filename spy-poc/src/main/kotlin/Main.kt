package com.gabrielspassos

import com.gabrielspassos.consumer.BankConsumer
import com.gabrielspassos.consumer.client.InMemoryQueueClient
import com.gabrielspassos.consumer.model.QueueMessage
import com.gabrielspassos.dao.BankDAO
import com.gabrielspassos.dto.BankDTO
import com.gabrielspassos.repository.inmemory.InMemoryBankRepository
import com.gabrielspassos.service.BankService
import com.google.gson.Gson
import java.util.UUID


private val gson = Gson()
private val bankRepository = InMemoryBankRepository()
private val bankDAO = BankDAO(bankRepository = bankRepository)
private val bankService = BankService(bankDAO = bankDAO)
private val queueService = InMemoryQueueClient()
private val bankConsumer = BankConsumer(bankService = bankService, queueService = queueService)

fun main() {
    try {
        println("Bank Basic POC")

        bankService.seed()

        processBank(1L)
        processBank(2L)
        processBank(3L)
        processBank(4L)

        bankConsumer.processSingleMessage()
        bankConsumer.processMultipleMessages()
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}

private fun processBank(id: Long) {
    bankService.findById(id).map {
        println("BankDTO $it")
        val message = mapToQueueMessage(it)
        println("QueueMessage $message")
        queueService.addMessage(message)
    }
}

private fun mapToQueueMessage(bankDTO: BankDTO): QueueMessage {
    val updatedBank = bankDTO.copy(code = bankDTO.code + "-queue")
    val body = gson.toJson(updatedBank)
    return QueueMessage(UUID.randomUUID().toString(), body)
}