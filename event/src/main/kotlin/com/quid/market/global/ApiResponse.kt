package com.quid.market.global

import org.springframework.http.HttpStatus

sealed class ApiResponse<T> {
    data class Success<T>(val data: T, val httpStatus: HttpStatus) : ApiResponse<T>()
    data class Error<T>(val message: String, val httpStatus: HttpStatus) : ApiResponse<T>()
}