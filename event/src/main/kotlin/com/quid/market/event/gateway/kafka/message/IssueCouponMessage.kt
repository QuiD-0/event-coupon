package com.quid.market.event.gateway.kafka.message

data class IssueCouponMessage(
    val eventId:Long,
    val userId:Long
) {
}