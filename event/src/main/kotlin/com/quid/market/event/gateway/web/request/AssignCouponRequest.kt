package com.quid.market.event.gateway.web.request

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.domain.CouponType
import java.time.LocalDate

data class AssignCouponRequest(
    val eventId: Long,
    val count: Int,
    val couponName: String,
    val type: String,
    val amount: Int,
    val expireDate: LocalDate,
) {
    val coupon: Coupon
        get() = Coupon(
            couponName = couponName,
            value = CouponType.of(type, amount),
            expireDate = expireDate
        )
}