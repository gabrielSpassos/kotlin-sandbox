package com.gabrielspassos.config

import com.gabrielspassos.annotation.ConvertUUIDToString
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.util.UUID
import kotlin.reflect.full.findAnnotation

@Component
class UUIDToStringConverter : Converter<UUID, String> {
    override fun convert(source: UUID): String {
        // Check if the field has the @ConvertUUIDToString annotation
        val field = source::class.members.find { it.findAnnotation<ConvertUUIDToString>() != null }
        return if (field != null) {
            source.toString()
        } else {
            // Handle the case where the annotation is not present
            source.toString()
        }
    }
}