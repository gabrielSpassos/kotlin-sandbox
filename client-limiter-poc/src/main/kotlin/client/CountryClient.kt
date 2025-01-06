package com.gabrielspassos.client

import com.gabrielspassos.client.response.Country
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class CountryClient(private val httpClient: HttpClient, private val mapper: Gson) {

    private val rootUrl = "https://restcountries.com"

    fun getCountryByName(name: String): Country {
        val request = HttpRequest.newBuilder()
            .uri(URI("$rootUrl/v3.1/name/$name"))
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .GET()
            .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        return mapper.fromJson(response.body(), Country::class.java)
    }

}