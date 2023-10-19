package com.quid.market.fixture

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.domain.FixedAmountCoupon
import java.time.LocalDate

class CouponFixture {

    fun fixedAmountCoupon() = Coupon(
        id = 1L,
        couponName = "쿠폰",
        value = FixedAmountCoupon(1000),
        expireDate = LocalDate.now().plusDays(7),
    )
}