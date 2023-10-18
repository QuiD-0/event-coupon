package com.quid.market.coupon.usecase

import com.quid.market.fixture.UserCouponFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UseCouponTest {

    private val userCouponRepository = UserCouponFixture().repository()
    private lateinit var useCoupon: UseCoupon.UseCouponUseCase

    @BeforeEach
    fun setUp() {
        useCoupon = UseCoupon.UseCouponUseCase(userCouponRepository)

        userCouponRepository.save(UserCouponFixture().userCoupon())
    }


    @Test
    fun use() {
        val originPrice = 10000
        val discount = useCoupon.use(1, originPrice)

        assertEquals(9000, discount)
    }
}