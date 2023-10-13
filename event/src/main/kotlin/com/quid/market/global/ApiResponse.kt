package com.quid.market.global

import java.time.LocalDateTime

sealed interface ApiResponse<RESPONSE> {
    fun getTimeStamp(): LocalDateTime = LocalDateTime.now()
}

data class Success<RESPONSE>(
    val data: RESPONSE
) : ApiResponse<RESPONSE>

data class Error<ERROR>(
    val message: String,
) : ApiResponse<ERROR>
