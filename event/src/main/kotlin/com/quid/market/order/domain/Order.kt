package com.quid.market.order.domain

import com.quid.market.order.usecase.RequestOrder
import java.time.LocalDateTime

data class Order(
    val id: Long? = null,
    val userId: Long,
    val originPrice: Int,
    val paymentPrice: Int,
    val itemList: List<RequestOrder.Item>,
    val userCouponId: Long? = null,
    val regDate : LocalDateTime = LocalDateTime.now(),
) {
}