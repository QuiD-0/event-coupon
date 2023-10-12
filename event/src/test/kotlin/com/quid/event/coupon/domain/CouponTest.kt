package com.quid.event.coupon.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class CouponTest{

    @Test
    fun typeTest(){
        val fixCoupon = Coupon(
            couponName = "FixedAmountCoupon",
            amount = FixedAmountCoupon(1000),
            regDate = LocalDate.now(),
            expireDate = LocalDate.now().plusDays(7)
        )

        val percentageCoupon = Coupon(
            couponName = "PercentageCoupon",
            amount = PercentageCoupon(20),
            regDate = LocalDate.now(),
            expireDate = LocalDate.now().plusDays(7)
        )

        assertEquals(9000, fixCoupon.discount(10000))
        assertEquals(8000, percentageCoupon.discount(10000))
    }
}