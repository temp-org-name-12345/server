package com.example.server.error

data class ErrorResponse(
    val code: String,
    val message: String,
    val state: Int
)