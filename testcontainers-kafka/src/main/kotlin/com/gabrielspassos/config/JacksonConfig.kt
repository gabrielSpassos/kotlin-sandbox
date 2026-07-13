package com.gabrielspassos.config

import org.springframework.context.annotation.Configuration
import tools.jackson.databind.ObjectMapper

@Configuration
class JacksonConfig(objectMapper: ObjectMapper) {
    init {
        objectMapper.registerKotlinModule()
    }
}
