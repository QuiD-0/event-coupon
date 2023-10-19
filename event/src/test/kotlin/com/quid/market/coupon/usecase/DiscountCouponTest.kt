package com.quid.market.coupon.usecase

import com.quid.market.fixture.CouponFixture
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DiscountCouponTest{

    @Test
    fun discount(){
        val discountCoupon = DiscountCoupon.DiscountCouponUseCase()
        val discount = discountCoupon.discount(CouponFixture().fixedAmountCoupon(), 10000)
        assertEquals(9000, discount)
    }
}