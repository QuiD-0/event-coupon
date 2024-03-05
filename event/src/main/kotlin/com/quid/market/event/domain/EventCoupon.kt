package com.quid.market.event.domain

import com.quid.market.coupon.domain.Coupon

data class EventCoupon(
    val count: Int,
    val coupon: Coupon? = null,
) {

    init {
        require(count >= 0) { "쿠폰이 모두 발급되었습니다." }
    }
}