package com.quid.market.event.domain

import java.time.LocalDateTime

data class Event(
    val id: Long? = null,
    val eventName: String,
    val description: String,
    val eventStartDate: LocalDateTime,
    val eventEndDate: LocalDateTime,
    val eventCoupon: EventCoupon = EventCoupon(),
    val regDate: LocalDateTime = LocalDateTime.now(),
) {
    init {
        require(eventStartDate.isBefore(eventEndDate)) { "이벤트의 종료일은 시작일보다 빠를 수 없습니다." }
    }

    fun issueCoupon(): Event {
        require(eventCoupon.isAssigned) { "등록된 쿠폰이 없습니다." }
        require( eventStartDate.isBefore(LocalDateTime.now()) ) { "이벤트가 시작되지 않았습니다." }
        require( eventEndDate.isAfter(LocalDateTime.now()) ) { "이벤트가 종료되었습니다." }
        return this.copy(eventCoupon = eventCoupon.decrease())
    }

    fun assignCoupon(coupon: EventCoupon): Event {
        return this.copy(eventCoupon = coupon)
    }
}