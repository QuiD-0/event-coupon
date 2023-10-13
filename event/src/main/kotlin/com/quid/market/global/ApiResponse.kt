package com.quid.market.global

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import java.time.LocalDateTime

sealed interface ApiResponse<RESPONSE> {
    val httpStatus: HttpStatus
    fun getTimeStamp(): LocalDateTime = LocalDateTime.now()
}

data class Created<RESPONSE>(
    val data: RESPONSE,
    override val httpStatus: HttpStatus = CREATED
) : ApiResponse<RESPONSE>

data class Success<RESPONSE>(
    val data: RESPONSE,
    override val httpStatus: HttpStatus = OK
) : ApiResponse<RESPONSE>

data class Error<ERROR>(
    val message: String,
    override val httpStatus: HttpStatus
) : ApiResponse<ERROR>
