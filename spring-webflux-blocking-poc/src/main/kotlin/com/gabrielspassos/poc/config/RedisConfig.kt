package com.gabrielspassos.poc.config

import io.lettuce.core.RedisClient
import io.lettuce.core.RedisURI
import io.lettuce.core.api.StatefulRedisConnection
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class RedisConfig {

    @Value($$"${spring.redis.host}")
    private val redisHost: String = ""

    @Value($$"${spring.redis.port}")
    private val redisPort: Int = 0

    @Bean
    fun connection(): StatefulRedisConnection<String, String> {
        val connection = buildClient().connect()
        return connection
    }

    private fun buildClient(): RedisClient {
        return RedisClient.create(
            RedisURI.Builder
                .redis(redisHost, redisPort)
                .withTimeout(Duration.ofSeconds(15))
                .withSsl(false)
                .withVerifyPeer(false)
                .build()
        )
    }
}