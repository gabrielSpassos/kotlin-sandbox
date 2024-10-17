package com.gabrielspassos.poc.consumer

import com.gabrielspassos.poc.service.PersonService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class PersonConsumer(
    @Autowired private val personService: PersonService,
) {

    @KafkaListener(topics = ["\${kafka.person-topic}"], groupId = "\${kafka.consumer-group}")
    fun consumerPersonTopic(consumerRecord: ConsumerRecord<Any, Any>, ack: Acknowledgment) {
        val personId = consumerRecord.value().toString()
        val personResponse = personService.findPersonById(personId)
        println("Consumed personId=$personId, and found person=$personResponse")
        ack.acknowledge()
    }

}