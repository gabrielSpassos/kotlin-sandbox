package com.gabrielspassos.config

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tools.jackson.module.kotlin.KotlinModule

@Configuration
class JacksonConfig() {

    @Bean
    fun jacksonCustomizer(): JsonMapperBuilderCustomizer {
        return JsonMapperBuilderCustomizer { builder ->
            builder.addModules(KotlinModule.Builder().build())
        }
    }

}
