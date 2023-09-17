package com.example.server.controller

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@RestController
class TestController {
    val data = listOf("1", "2", "3", "4")

    @GetMapping("/test1")
    suspend fun test1() : String {
        return "Tesk OK"
    }

    @GetMapping("/test2")
    fun test2() : Flow<String> {
        return data.asFlow()
    }
}