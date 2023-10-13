package com.quid.market.global

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import java.time.LocalDateTime

sealed interface ApiResponse<RESPONSE> {
    fun getTimeStamp(): LocalDateTime = LocalDateTime.now()
}

data class Success<RESPONSE>(
    val data: RESPONSE
) : ApiResponse<RESPONSE>

data class Error<ERROR>(
    val message: String
) : ApiResponse<ERROR>
