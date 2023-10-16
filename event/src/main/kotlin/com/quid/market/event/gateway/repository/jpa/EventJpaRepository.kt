package com.quid.market.event.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import javax.persistence.LockModeType

interface EventJpaRepository: JpaRepository<EventEntity, Long> {

    @Modifying
    @Query("delete from EventEntity")
    override fun deleteAll()

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from EventEntity e where e.id = :eventId")
    fun findByIdOrNullForUpdate(eventId: Long): EventEntity?
}