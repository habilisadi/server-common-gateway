package com.habilisadi.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.server.ServerWebExchange

@Configuration
class CorsConfig {

    @Bean
    fun corsWebFilter(): CorsWebFilter {
        val source = CorsConfigurationSource { request: ServerWebExchange? ->
            val corsConfiguration = CorsConfiguration()
            corsConfiguration.allowedOrigins =
                listOf(
                    "http://localhost:3000",
                    "https://localhost:3000",
                    "http://localhost:8080",
                    "https://localhost:8080"
                )
            corsConfiguration.allowedMethods =
                listOf("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
            corsConfiguration.allowedHeaders = listOf("Authorization", "Content-Type")
            corsConfiguration.allowCredentials = true
            corsConfiguration.maxAge = 3600L
            corsConfiguration
        }

        return CorsWebFilter(source)
    }
}