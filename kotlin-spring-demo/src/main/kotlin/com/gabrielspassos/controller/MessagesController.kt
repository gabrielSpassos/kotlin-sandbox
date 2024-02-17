package com.gabrielspassos.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MessagesController {

    @GetMapping("/names")
    fun getNames(@RequestParam("name", required = false) name: String?): String {
        val finalName = name ?: "Kotlin Spring Demo User"

        return "Hello $finalName!"
    }

}