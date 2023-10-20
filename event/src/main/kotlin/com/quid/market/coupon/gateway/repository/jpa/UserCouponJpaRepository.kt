package com.quid.market.coupon.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import javax.persistence.LockModeType

interface UserCouponJpaRepository: JpaRepository<UserCouponEntity, Long> {
    fun findByUserId(userId: Long): List<UserCouponEntity>
    fun existsByUserIdAndCoupon(userId: Long, coupon: CouponEntity): Boolean
    @Modifying
    @Query("delete from UserCouponEntity")
    override fun deleteAll()

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select uc from UserCouponEntity uc where uc.id = :userCouponId")
    fun findByIdOrNullForUpdate(userCouponId: Long): UserCouponEntity?
}