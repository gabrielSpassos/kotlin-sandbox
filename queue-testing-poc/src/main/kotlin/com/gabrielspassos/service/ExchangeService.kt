package com.gabrielspassos.service

import com.gabrielspassos.producer.ExchangeProducer
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class ExchangeService(private val producer: ExchangeProducer)  {
}