package com.gabrielspassos.poc.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.JdkClientHttpRequestFactory
import org.springframework.web.client.RestClient
import java.net.http.HttpClient
import java.time.Duration

@Configuration
class ExchangeClientConfig {
    @Bean
    fun restClientBuilder(): RestClient.Builder {
        return RestClient.builder()
    }

    @Bean
    fun exchangeRestClient(builder: RestClient.Builder, @Value($$"${exchange.api.url}") url: String): RestClient {
        val httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build()
        val requestFactory = JdkClientHttpRequestFactory(httpClient)
        requestFactory.setReadTimeout(Duration.ofSeconds(5))

        return builder
            .baseUrl(url)
            .requestFactory(requestFactory)
            .build()
    }
}