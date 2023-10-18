package com.quid.market.coupon.usecase

import com.quid.market.coupon.gateway.repository.UserCouponRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface UseCoupon {
    fun use(userCouponId: Long, price: Int): Int

    @Service
    @Transactional
    class UseCouponUseCase(
        val userCouponRepository: UserCouponRepository,
    ) : UseCoupon {

        override fun use(userCouponId: Long, price: Int): Int =
            userCouponRepository.findById(userCouponId)
                .use()
                .let { userCouponRepository.save(it) }
                .discount(price)
    }
}