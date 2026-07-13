package com.gabrielspassos.config

import com.gabrielspassos.event.UserEvent
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.kafka.autoconfigure.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinModule

@Configuration
class KafkaConsumerConfig {

    @Bean
    fun consumerFactory(kafkaProperties: KafkaProperties): ConsumerFactory<String, UserEvent> {
        val jsonMapper = JsonMapper.builder()
            .addModule(KotlinModule.Builder().build())
            .build()

        val jacksonDeserializer = JacksonJsonDeserializer(UserEvent::class.java, jsonMapper)

        val props = kafkaProperties.buildConsumerProperties()

        return DefaultKafkaConsumerFactory(
            props,
            StringDeserializer(),
            jacksonDeserializer
        )
    }

    @Bean(name = ["kafkaListenerContainerFactory"])
    fun kafkaListenerContainerFactory(
        consumerFactory: ConsumerFactory<String, UserEvent>
    ): ConcurrentKafkaListenerContainerFactory<String, UserEvent> {
        return ConcurrentKafkaListenerContainerFactory<String, UserEvent>().apply {
            setConsumerFactory(consumerFactory)
        }
    }
}
