package com.quid.market.order.gateway.web.request


data class CreateOrderRequest(
    val userId: Long,
    val itemListId: List<Long>,
    val userCouponId: Long? = null,
) {
}