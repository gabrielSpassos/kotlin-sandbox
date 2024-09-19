package com.gabrielspassos

import java.util.Properties


class PropertiesReader(propertyFileName: String) {

    private var properties: Properties

    init {
        val inputStream = javaClass.classLoader.getResourceAsStream(propertyFileName)
        this.properties = Properties()
        this.properties.load(inputStream)
    }

    fun getProperty(propertyName: String): String {
        return this.properties.getProperty(propertyName)
    }
}