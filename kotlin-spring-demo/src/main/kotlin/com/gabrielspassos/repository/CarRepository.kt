package com.gabrielspassos.repository

import com.gabrielspassos.dto.CarDTO
import org.springframework.data.repository.CrudRepository

interface CarRepository: CrudRepository<CarDTO, String> {
}