package com.quid.market.coupon.domain

import java.time.LocalDate

data class Coupon(
    val id: Long? = null,
    val couponName: String,
    val value: CouponType,
    val expireDate: LocalDate,
    val regDate: LocalDate = LocalDate.now(),
    ) {
    val isExpired: Boolean
        get() = LocalDate.now().isAfter(expireDate)

    init {
        require(regDate.isBefore(expireDate)) { "쿠폰의 만료일은 등록일보다 빠를 수 없습니다." }
    }

    fun discount(origin: Int): Int = value.discount(origin)
}