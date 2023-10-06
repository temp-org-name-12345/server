package com.example.server.error

import com.example.server.error.exception.ImageRemovalException
import com.example.server.error.exception.MultipartFileNullException
import com.example.server.error.exception.UserNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@ResponseStatus(HttpStatus.BAD_REQUEST)
@RestControllerAdvice
class ExceptionHandler {
    companion object {
        private val log = LoggerFactory.getLogger(ExceptionHandler::class.java)

        data class ErrorResponse(
            val code: String,
            val message: String,
            val state: Int
        )

        private fun buildError(err: ErrorCode) : ErrorResponse {
            log.error("[cause] : ${err.message}")

            return ErrorResponse(
                code = err.code,
                message = err.message,
                state = err.status
            )
        }
    }

    @ExceptionHandler(UserNotFoundException::class)
    protected fun handleUserNotFoundException(e: UserNotFoundException) = buildError(ErrorCode.USER_NOT_FOUND)

    @ExceptionHandler(MultipartFileNullException::class)
    protected fun handleMultipartFileNullException(e: MultipartFileNullException) = buildError(ErrorCode.MULTIPART_FILE_IS_NULL)

    @ExceptionHandler(ImageRemovalException::class)
    protected fun handleImageRemovalException(e: ImageRemovalException) = buildError(ErrorCode.IMAGE_REMOVE_FAIL)
}