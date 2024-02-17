package com.gabrielspassos.service

import com.gabrielspassos.dto.CarDTO
import com.gabrielspassos.repository.CarRepository
import org.springframework.stereotype.Service

@Service
class CarService(val db: CarRepository) {

    fun findCars(): List<CarDTO> {
        return db.findAll().toList()
    }

    fun findCarById(id: String): CarDTO {
        return db.findById(id).orElseThrow { RuntimeException("Not found car by Id $id") }
    }

    fun saveCar(car: CarDTO): CarDTO {
        return db.save(car)
    }

}