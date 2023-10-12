package com.quid.event.coupon.domain

import java.time.LocalDate

data class Coupon(
    val id: String? = null,
    val couponName: String,
    val amount: CouponType,
    val regDate: LocalDate,
    val expireDate: LocalDate,
    val isUsed: Boolean = false,
    val usedDate: LocalDate? = null
    ) {

    fun discount(origin: Int): Int = amount.discount(origin)
}