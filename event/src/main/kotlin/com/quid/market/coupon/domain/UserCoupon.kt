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

    fun use(): UserCoupon {
        require(!isUsed) { "이미 사용된 쿠폰입니다." }
        return this.copy(usedDate = LocalDate.now())
    }

}