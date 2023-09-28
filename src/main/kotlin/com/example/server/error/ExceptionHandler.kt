package com.example.server.error

import com.example.server.error.exception.UserNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    companion object {
        private val log = LoggerFactory.getLogger(ExceptionHandler::class.java)

        private fun buildError(errorCode: ErrorCode) : ErrorResponse {
            return ErrorResponse(
                code = errorCode.code,
                message = errorCode.message,
                state = errorCode.status
            )
        }
    }

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected fun handleUserNotFoundException(e: UserNotFoundException) : ErrorResponse {
        log.error("[cause] obj : ${e.obj}")
        return buildError(ErrorCode.USER_NOT_FOUND)
    }
}