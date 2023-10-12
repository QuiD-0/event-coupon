package com.quid.market.coupon.domain

import java.time.LocalDate

data class Coupon(
    val id: Long? = null,
    val userId: Long,
    val couponName: String,
    val amount: CouponType,
    val regDate: LocalDate,
    val expireDate: LocalDate,
    val usedDate: LocalDate? = null
    ) {
    val isUsed: Boolean
        get() = usedDate != null
    val isExpired: Boolean
        get() = LocalDate.now().isAfter(expireDate)

    init {
        require(regDate.isBefore(expireDate)) { "regDate must be before expireDate" }
    }

    fun discount(origin: Int): Int = amount.discount(origin)

    fun useCoupon(): Coupon {
        require(!isUsed) { "already used coupon" }
        require(!isExpired) { "expired coupon" }
        return this.copy(usedDate = LocalDate.now())
    }
}