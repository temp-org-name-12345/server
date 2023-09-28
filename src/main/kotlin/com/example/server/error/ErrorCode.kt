package com.example.server.error

enum class ErrorCode(
    val code: String,
    val message: String,
    val status: Int = 400
) {
    USER_NOT_FOUND("USER_NOT_FOUND", "해당 유저를 찾을 수 없습니다.")
}