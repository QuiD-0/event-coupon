package com.quid.market.event.domain

import com.quid.market.coupon.domain.Coupon

data class RemainCoupon(
    val count: Int = 0,
    val coupon: Coupon? = null,
) {
    val isAssigned: Boolean
        get() = coupon != null

    init {
        require(count >= 0) { "쿠폰이 모두 발급되었습니다." }
    }

    fun decrease(): RemainCoupon {
        return this.copy(count = count - 1)
    }
}