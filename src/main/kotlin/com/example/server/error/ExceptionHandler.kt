package com.example.server.error

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    /*
    @ExceptionHandler(Exception::class)
    suspend fun handleException() : String {
        return "에러 핸들링 OK"
    }
    */
}