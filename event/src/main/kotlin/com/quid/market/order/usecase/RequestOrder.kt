package com.quid.market.order.usecase

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.repository.UserCouponRepository
import com.quid.market.coupon.usecase.UseCoupon
import com.quid.market.order.domain.Order
import com.quid.market.order.gateway.web.request.CreateOrderRequest
import com.quid.market.order.gateway.web.response.OrderInfo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface RequestOrder {
    fun createOrder(request: CreateOrderRequest): OrderInfo

    @Service
    @Transactional
    class RequestOrderUseCase(
        val useCoupon: UseCoupon, val userCouponRepository: UserCouponRepository
    ) : RequestOrder {
        val orderRepository: MockOrderRepository = MockOrderRepository()
        val itemRepository: MockItemRepository = MockItemRepository()


        override fun createOrder(request: CreateOrderRequest): OrderInfo {
            val userCoupon: UserCoupon? =
                request.userCouponId?.let { userCouponRepository.findById(it) }
            val itemList = itemRepository.findByIdIn(request.itemListId)
            val originPrice = itemList.sumOf { it.price }

            userCoupon?.let { useCoupon.use(it.id!!) }

            val order = Order(
                userId = request.userId,
                itemList = itemList,
                userCouponId = userCoupon?.id,
                originPrice = originPrice,
                paymentPrice = userCoupon?.coupon?.discount(originPrice) ?: originPrice,
            ).let { orderRepository.save(it) }

            return OrderInfo(
                userId = order.userId,
                originPrice = order.originPrice,
                itemList = order.itemList,
                userCoupon = userCoupon,
                regDate = order.regDate,
            )
        }
    }


    class MockOrderRepository {
        fun save(it: Order): Order {
            return it.copy(id = 1L)

        }
    }

    class MockItemRepository {
        fun findByIdIn(itemListId: List<Long>): List<Item> {
            return listOf(Item("item1", 10000), Item("item2", 20000))
        }
    }

    data class Item(
        val name: String,
        val price: Int,
    )
}