package com.quid.market.event.usecase

import com.quid.market.event.domain.Event

fun interface RegistEvent {

    fun persist(event: Event): Event

}