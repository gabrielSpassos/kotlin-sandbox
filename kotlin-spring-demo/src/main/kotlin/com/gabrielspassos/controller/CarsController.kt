package com.gabrielspassos.controller

import com.gabrielspassos.dto.CarDTO
import com.gabrielspassos.service.CarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/cars"])
class CarsController(val carService: CarService) {

    @GetMapping
    fun getCars() = carService.findCars()

    @GetMapping("/{id}")
    fun getCarById(@PathVariable("id") id: String) = carService.findCarById(id)

    @PostMapping
    fun createCar(@RequestBody car: CarDTO) = carService.saveCar(car)
}