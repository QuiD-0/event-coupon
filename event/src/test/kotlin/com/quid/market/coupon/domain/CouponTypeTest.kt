package com.quid.market.coupon.domain

import org.junit.jupiter.api.Test

class CouponTypeTest {

    @Test
    fun typeToString(){
        val couponType = FixedAmountCoupon(1000)

        assert(couponType.type() == "FixedAmountCoupon")
    }
}