package com.gabrielspassos

import com.gabrielspassos.factory.BeanFactory


fun main() {
    println("Client Limiter POC")

    val countryClient = BeanFactory.buildCountryClient()

    val brazil = countryClient.getCountryByName("brazil")
    println("Brazil country $brazil")
}