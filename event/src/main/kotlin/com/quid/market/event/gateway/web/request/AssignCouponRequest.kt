package com.quid.market.event.gateway.web.request

import com.quid.market.event.domain.EventCoupon

data class AssignCouponRequest(
    val eventId: Long,
    val count: Int,
    val couponId: Long,
) {
    val eventCoupon: EventCoupon
        get() = EventCoupon(count, couponId)
}