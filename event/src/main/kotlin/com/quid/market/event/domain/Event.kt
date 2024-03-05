package com.quid.market.event.domain

import java.time.LocalDateTime

data class Event(
    val id: Long? = null,
    val eventName: String,
    val description: String,
    val eventStartDate: LocalDateTime,
    val eventEndDate: LocalDateTime,
    val regDate: LocalDateTime = LocalDateTime.now(),
    val couponId: Long? = null,
    val maxCount: Int? = null
) {
    fun assignCoupon(couponId: Long, maxCount: Int): Event =
        copy(couponId = couponId, maxCount = maxCount)

    init {
        require(eventStartDate.isBefore(eventEndDate)) { "이벤트의 종료일은 시작일보다 빠를 수 없습니다." }
    }

}