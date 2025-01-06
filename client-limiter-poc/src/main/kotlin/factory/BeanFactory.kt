package com.gabrielspassos.factory

import com.gabrielspassos.client.CountryClient
import com.google.gson.Gson
import java.net.http.HttpClient

object BeanFactory {

    fun buildCountryClient(): CountryClient {
        return CountryClient(buildHttpClient(), buildGsonMapper())
    }

    private fun buildHttpClient(): HttpClient {
        return HttpClient.newBuilder().build()
    }

    private fun buildGsonMapper(): Gson {
        return Gson()
    }
}