package com.quid.market.global

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ofPattern

sealed interface ApiResponse<RESPONSE> {
    val timeStamp: String
        get() = LocalDateTime.now().format(ofPattern("yyyy-MM-dd HH:mm:ss"))
}

data class Success<RESPONSE>(
    val data: RESPONSE
) : ApiResponse<RESPONSE>

data class Error<ERROR>(
    val message: String,
) : ApiResponse<ERROR>
