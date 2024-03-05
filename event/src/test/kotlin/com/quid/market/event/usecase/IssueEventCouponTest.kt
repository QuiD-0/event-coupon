package com.quid.market.event.usecase

import com.quid.market.coupon.gateway.repository.CouponRepository
import com.quid.market.coupon.gateway.repository.UserCouponRepository
import com.quid.market.event.gateway.kafka.producer.IssueEventCouponProducer
import com.quid.market.event.gateway.repository.EventRepository
import com.quid.market.fixture.EventFixture
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@Disabled
@SpringBootTest
class IssueEventCouponTest{

    @Autowired
    lateinit var eventRepository: EventRepository
    @Autowired
    lateinit var userCouponRepository: UserCouponRepository
    @Autowired
    lateinit var couponRepository: CouponRepository
    @Autowired
    lateinit var producer: IssueEventCouponProducer

    @Test
    fun issueEventCouponTest(){
        val issueEventCoupon: IssueEventCoupon = IssueEventCoupon.IssueEventCouponUseCase(eventRepository, couponRepository, userCouponRepository, producer)

        userCouponRepository.deleteAll()
        eventRepository.save(EventFixture().event)

        val threadPool = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(100)

        for(i in 1..100){
            threadPool.submit {
                try {
                    issueEventCoupon.execute(i.toLong(), 1)
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()

        println("remain coupon count: ${eventRepository.issuedCount(1)}")
    }
}