package com.quid.market.event.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface EventJpaRepository: JpaRepository<EventEntity, Long> {

    @Modifying
    @Query("delete from EventEntity")
    override fun deleteAll()
}