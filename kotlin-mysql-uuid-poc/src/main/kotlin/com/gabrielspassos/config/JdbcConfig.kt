package com.gabrielspassos.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration

@Configuration
class JdbcConfig : AbstractJdbcConfiguration() {
    override fun userConverters(): List<Any> {
        return listOf(UUIDToStringConverter(), StringToUUIDConverter())
    }
}