package com.quid.market.coupon.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class CouponTest {

    @Test
    @DisplayName("쿠폰 할인 테스트")
    fun typeTest() {
        val fixCoupon = Coupon(
            couponName = "FixedAmountCoupon",
            value = FixedAmountCoupon(1000),
            regDate = LocalDate.now(),
            expireDate = LocalDate.now().plusDays(7)
        )

        val percentageCoupon = Coupon(
            couponName = "PercentageCoupon",
            value = PercentageCoupon(20),
            regDate = LocalDate.now(),
            expireDate = LocalDate.now().plusDays(7)
        )

        assertEquals(9000, fixCoupon.discount(10000))
        assertEquals(8000, percentageCoupon.discount(10000))
    }

    @Test
    @DisplayName("쿠폰 만료일이 지났을 경우")
    fun expiredTest() {
        val coupon = Coupon(
            couponName = "FixedAmountCoupon",
            value = FixedAmountCoupon(1000),
            regDate = LocalDate.now().minusDays(7),
            expireDate = LocalDate.now().minusDays(1)
        )

        assertTrue(coupon.isExpired)
    }

    @Test
    @DisplayName("쿠폰 만료일은 등록일보다 빠를 수 없다.")
    fun makeCouponFail() {
        assertThrows<IllegalArgumentException> {
            Coupon(
                couponName = "FixedAmountCoupon",
                value = FixedAmountCoupon(1000),
                regDate = LocalDate.now(),
                expireDate = LocalDate.now().minusDays(1)
            )
        }
    }

}