package com.quid.market.global

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorHandling {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<Error<String>> {
        ex.printStackTrace()
        val message = ex.message ?: "Unknown Error"
        val statusCode = determineStatusCode(ex)
        return ResponseEntity(Error(message), statusCode)
    }

    private fun determineStatusCode(ex: Exception): HttpStatus {
        return when (ex) {
            is IllegalArgumentException -> BAD_REQUEST
            is NoSuchElementException -> NOT_FOUND
            is IllegalStateException -> CONFLICT
            is RuntimeException -> INTERNAL_SERVER_ERROR
            else -> INTERNAL_SERVER_ERROR
        }
    }

}