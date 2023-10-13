package com.quid.market.coupon.domain

import java.time.LocalDate

data class UserCoupon(
    val id: Long? = null,
    val userId: Long,
    val coupon: Coupon,
    val usedDate: LocalDate? = null,
    val regDate: LocalDate = LocalDate.now(),
) {
    val isUsed: Boolean
        get() = usedDate != null
}