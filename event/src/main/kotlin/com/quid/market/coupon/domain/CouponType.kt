package com.quid.market.coupon.domain

sealed interface CouponType {
    fun discount(origin: Int): Int
    fun value(): Int
    fun type(): String = this::class.simpleName!!

    companion object {
        fun of(type: String, value: Int): CouponType {
            return when (type) {
                "FixedAmountCoupon" -> FixedAmountCoupon(value)
                "PercentageCoupon" -> PercentageCoupon(value)
                else -> throw IllegalArgumentException("CouponType not found")
            }
        }
    }
}

data class FixedAmountCoupon(
    val amount: Int
) : CouponType {
    override fun discount(origin: Int): Int = maxOf(origin - amount, 0)
    override fun value(): Int = amount
}

data class PercentageCoupon(
    val percent: Int
) : CouponType {
    override fun discount(origin: Int): Int = origin - (origin * percent / 100)
    override fun value(): Int = percent
}