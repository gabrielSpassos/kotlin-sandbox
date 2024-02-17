package com.gabrielspassos.dto

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CARS")
data class CarDTO(@Id var id: String?, val model: String)