package com.quid.market.global

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorHandling {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ApiResponse<String> =
        ApiResponse.Error(e.message ?: "Unknown Error", HttpStatus.INTERNAL_SERVER_ERROR)

}