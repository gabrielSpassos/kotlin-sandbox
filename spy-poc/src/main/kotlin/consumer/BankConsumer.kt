package com.gabrielspassos.consumer

import com.gabrielspassos.consumer.client.QueueService
import com.gabrielspassos.dto.BankDTO
import com.gabrielspassos.service.BankService
import com.google.gson.Gson

class BankConsumer(private val bankService: BankService, private val queueService: QueueService) {

    private val gson = Gson()
    private val queueName = "bank-queue"

    fun processSingleMessage(): Boolean {
        val queueMessage = queueService.consumeMessage(queueName)
        return queueMessage.map { message ->
            val bankDTO = gson.fromJson(message.body, BankDTO::class.java)
            bankService.update(bankDTO)
            println("Consumer bank: $bankDTO")
            queueService.deleteMessage(queueName, message.id)
        }.orElse(false)
    }

    fun processMultipleMessages(): Boolean {
        return queueService.consumeMessages(queueName, 5)
            .map { message ->
                val bankDTO = gson.fromJson(message.body, BankDTO::class.java)
                bankService.update(bankDTO)
                println("Consumer bank: $bankDTO")
                queueService.deleteMessage(queueName, message.id)
            }.all { result -> result }
    }
}