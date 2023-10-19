package com.quid.market.coupon.usecase

import com.quid.market.coupon.domain.UserCoupon
import com.quid.market.coupon.gateway.repository.UserCouponRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface UseCoupon {
    fun use(userCouponId: Long): UserCoupon

    @Service
    @Transactional
    class UseCouponUseCase(
        val userCouponRepository: UserCouponRepository,
    ) : UseCoupon {

        override fun use(userCouponId: Long): UserCoupon =
            userCouponRepository.findById(userCouponId)
                .use()
                .let { userCouponRepository.save(it) }
    }
}