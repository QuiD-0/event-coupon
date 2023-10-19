package com.quid.market.coupon.usecase

import com.quid.market.coupon.domain.Coupon
import org.springframework.stereotype.Service

interface DiscountCoupon {
    fun discount(coupon: Coupon, price: Int) : Int

    @Service
    class DiscountCouponUseCase : DiscountCoupon {
        override fun discount(coupon: Coupon, price: Int): Int =
            coupon.discount(price)
    }
}