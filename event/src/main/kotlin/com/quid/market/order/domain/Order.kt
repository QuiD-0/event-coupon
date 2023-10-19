package com.quid.market.order.domain

import com.quid.market.coupon.domain.UserCoupon
import java.time.LocalDateTime

data class Order(
    val id: Long,
    val userId: Long,
    val originPrice: Int,
    val userCoupon: UserCoupon? = null,
    val itemList: List<String>,
    val regDate : LocalDateTime,
) {
    val totalPrice: Int
        get() = userCoupon
            ?.coupon?.discount(originPrice)
            ?: originPrice

}