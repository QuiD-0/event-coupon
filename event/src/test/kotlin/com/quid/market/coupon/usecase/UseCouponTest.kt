package com.quid.market.coupon.usecase

import com.quid.market.fixture.UserCouponFixture
import org.junit.jupiter.api.Test

class UseCouponTest {

    private val userCouponRepository = UserCouponFixture().repository()
    private val useCoupon: UseCoupon = UseCoupon.UseCouponUseCase(userCouponRepository)



    @Test
    fun use() {
        userCouponRepository.save(UserCouponFixture().userCoupon())

        useCoupon.use(1)

        val usedCoupon = userCouponRepository.findById(1)
        assert(usedCoupon.isUsed)
    }
}