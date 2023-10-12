package com.quid.event.coupon.domain

sealed interface CouponType {
    fun discount(origin: Int): Int
}

data class FixedAmountCoupon(
    val amount: Int
) : CouponType {
    override fun discount(origin: Int): Int = maxOf(origin - amount, 0)
}

data class PercentageCoupon(
    val percent: Int
) : CouponType {
    override fun discount(origin: Int): Int = origin - (origin * percent / 100)
}