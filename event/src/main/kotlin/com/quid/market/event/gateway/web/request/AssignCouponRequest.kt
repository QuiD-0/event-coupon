package com.quid.market.event.gateway.web.request

data class AssignCouponRequest(
    val eventId: Long,
    val count: Int,
    val couponId: Long,
) {
}