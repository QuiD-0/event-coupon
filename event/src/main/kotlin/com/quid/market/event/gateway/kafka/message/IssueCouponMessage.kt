package com.quid.market.event.gateway.kafka.message

data class IssueCouponMessage(
    private val eventId:Long,
    private val userId:Long
) {
}