package com.habilisadi.gateway.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FallbackController {

    @GetMapping("/fallback")
    fun fallback(): ResponseEntity<Map<String, Any>> {
        val response = mapOf(
            "message" to "서비스가 일시적으로 사용할 수 없습니다. 잠시 후 다시 시도해주세요.",
            "status" to "SERVICE_UNAVAILABLE",
            "timestamp" to System.currentTimeMillis()
        )
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response)
    }
}