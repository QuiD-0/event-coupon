package com.quid.market.order.gateway.web.response

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.order.usecase.RequestOrder
import java.time.LocalDateTime

class OrderInfo(
    val userId: Long,
    val originPrice: Int,
    val itemList: List<RequestOrder.Item>,
    val userCoupon: UserCoupon? = null,
    val regDate : LocalDateTime,
) {
    val isCouponUsed: Boolean
        get() = userCoupon?.isUsed?:false

    val totalPrice: Int
        get() = userCoupon?.let { it.coupon.discount(originPrice) } ?: originPrice
}