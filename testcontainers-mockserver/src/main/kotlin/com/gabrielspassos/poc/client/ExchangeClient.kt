package com.gabrielspassos.poc.client

import com.gabrielspassos.poc.client.response.UsdResponse
import com.gabrielspassos.poc.exception.NotFoundException
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import java.math.BigDecimal

@Component
class ExchangeClient(private val restClient: RestClient) {

    val usdToBrl: UsdResponse?
        get() {
            try {
                val response: UsdResponse? = restClient.get()
                    .uri("/npm/@fawazahmed0/currency-api@latest/v1/currencies/usd.json")
                    .retrieve()
                    .body<UsdResponse>()

                if (response == null || response.usd == null) {
                    throw NotFoundException("Could not retrieve exchange rate", "NOT_FOUND_EXCHANGE")
                }

                return response
            } catch (e: Exception) {
                IO.println("Error $e")
                throw e
            }
        }
}