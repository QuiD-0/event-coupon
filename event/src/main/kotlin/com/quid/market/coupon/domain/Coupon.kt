package com.quid.market.coupon.domain

import java.time.LocalDate

data class Coupon(
    val id: Long? = null,
    val couponName: String,
    val amount: CouponType,
    val expireDate: LocalDate,
    val regDate: LocalDate = LocalDate.now(),
    ) {
    val isExpired: Boolean
        get() = LocalDate.now().isAfter(expireDate)

    init {
        require(regDate.isBefore(expireDate)) { "regDate must be before expireDate" }
    }

    fun discount(origin: Int): Int = amount.discount(origin)
}