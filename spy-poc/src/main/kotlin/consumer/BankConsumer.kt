package com.gabrielspassos.consumer

import com.gabrielspassos.consumer.client.QueueService
import com.gabrielspassos.dto.BankDTO
import com.gabrielspassos.service.BankService
import com.google.gson.Gson

class BankConsumer(private val bankService: BankService, private val queueService: QueueService) {

    private val gson = Gson()

    fun processSingleMessage(): Boolean {
        val queueMessage = queueService.consumeMessage()
        return queueMessage.map { message ->
            val bankDTO = gson.fromJson(message.body, BankDTO::class.java)
            bankService.update(bankDTO)
            println("Consumer bank: $bankDTO")
            queueService.deleteMessage(message.id)
        }.orElse(false)
    }

    fun processMultipleMessages(): Boolean {
        return queueService.consumeMessages(5)
            .map { message ->
                val bankDTO = gson.fromJson(message.body, BankDTO::class.java)
                bankService.update(bankDTO)
                println("Consumer bank: $bankDTO")
                queueService.deleteMessage(message.id)
            }.all { result -> result }
    }
}