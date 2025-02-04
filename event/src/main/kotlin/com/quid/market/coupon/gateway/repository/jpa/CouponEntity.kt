package com.quid.market.coupon.gateway.repository.jpa

import com.quid.market.coupon.domain.Coupon
import com.quid.market.coupon.domain.CouponType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "coupon")
class CouponEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null,
    val couponName: String,
    val value: Int,
    val type: String,
    val expireDate: LocalDate,
    val regDate: LocalDate,
) {

    fun toCoupon() = Coupon(
        id = id,
        couponName = couponName,
        value = CouponType.of(type, value),
        expireDate = expireDate,
        regDate = regDate,
    )
}

fun toCouponEntity(coupon: Coupon) = CouponEntity(
    id = coupon.id,
    couponName = coupon.couponName,
    value = coupon.value.value(),
    type = coupon.value.type(),
    expireDate = coupon.expireDate,
    regDate = coupon.regDate,
)
