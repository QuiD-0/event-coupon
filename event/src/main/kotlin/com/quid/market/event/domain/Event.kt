package com.quid.market.event.domain

import java.time.LocalDateTime

data class Event(
    val id: Long? = null,
    val eventName: String,
    val description: String,
    val eventStartDate: LocalDateTime,
    val eventEndDate: LocalDateTime,
    val couponRemainCount: Int,
    val couponId: Long? = null,
    val regDate: LocalDateTime = LocalDateTime.now(),
) {
    init {
        require(eventStartDate.isBefore(eventEndDate)) { "eventStartDate must be before eventEndDate" }
    }

    fun issueCoupon(): Event {
        require(couponRemainCount > 0) { "couponRemainCount must be greater than 0" }
        return this.copy(couponRemainCount = couponRemainCount - 1)
    }
}