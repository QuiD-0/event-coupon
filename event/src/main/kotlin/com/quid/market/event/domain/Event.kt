package com.quid.market.event.domain

import java.time.LocalDateTime

data class Event(
    val id: Long? = null,
    val eventName: String,
    val description: String,
    val eventStartDate: LocalDateTime,
    val eventEndDate: LocalDateTime,
    val coupon: RemainCoupon = RemainCoupon(),
    val regDate: LocalDateTime = LocalDateTime.now(),
) {
    init {
        require(eventStartDate.isBefore(eventEndDate)) { "이벤트의 종료일은 시작일보다 빠를 수 없습니다." }
    }

    fun issueCoupon(): Event {
        require(coupon.isAssigned) { "등록된 쿠폰이 없습니다." }
        return this.copy(coupon = coupon.decrease())
    }

    fun assignCoupon(coupon: RemainCoupon): Event {
        return this.copy(coupon = coupon)
    }
}