package com.quid.market.event.domain

data class EventCoupon(
    val count: Int,
    val couponId: Long,
) {

    init {
        require(count >= 0) { "쿠폰이 모두 발급되었습니다." }
    }
}