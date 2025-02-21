package com.gabrielspassos.config

import com.gabrielspassos.annotation.ConvertUUIDToString
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.util.UUID
import kotlin.reflect.full.findAnnotation

@Component
class StringToUUIDConverter : Converter<String, Any> {
    override fun convert(source: String): Any {
        // Check if the field has the @ConvertUUIDToString annotation
        val field = source::class.members.find { it.findAnnotation<ConvertUUIDToString>() != null }
        return if (field != null) {
            UUID.fromString(source)
        } else {
            // Handle the case where the annotation is not present
            source.toString()
        }
    }
}