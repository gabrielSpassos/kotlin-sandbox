package com.gabrielspassos.consumer

import com.gabrielspassos.consumer.client.QueueService
import com.gabrielspassos.consumer.model.QueueMessage
import com.gabrielspassos.dto.BankDTO
import com.gabrielspassos.service.BankService
import com.google.gson.Gson

class BankConsumer(private val bankService: BankService, private val queueService: QueueService) {

    private val gson = Gson()
    private val queueName = "bank-queue"
    private val dlqName = "bank-dlq-queue"

    fun processSingleMessage(): Boolean {
        val queueMessage = queueService.consumeMessage(queueName)
        return queueMessage.map { processMessage(it) }.orElse(false)
    }

    fun processMultipleMessages(): Boolean {
        return queueService.consumeMessages(queueName, 5)
            .map { processMessage(it) }
            .all { result -> result }
    }

    private fun processMessage(message: QueueMessage): Boolean {
        try {
            val bankDTO = gson.fromJson(message.body, BankDTO::class.java)
            bankService.update(bankDTO)
            println("Consumer bank: $bankDTO")
            return queueService.deleteMessage(queueName, message.id)
        } catch (e: Exception) {
            println("Error to process message: ${e.message}")
            queueService.addMessage(dlqName, message)
            return false
        }
    }
}