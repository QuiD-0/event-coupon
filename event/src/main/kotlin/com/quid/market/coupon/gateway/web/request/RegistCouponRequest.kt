package com.quid.market.coupon.gateway.web.request

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.domain.CouponType
import java.time.LocalDate

data class RegistCouponRequest(
    val couponName: String,
    val type: String,
    val amount: Int,
    val expireDate: LocalDate,
) {
    val coupon: Coupon
        get() = Coupon(
            couponName = couponName,
            value = CouponType.of(type, amount),
            expireDate = expireDate,
        )
}