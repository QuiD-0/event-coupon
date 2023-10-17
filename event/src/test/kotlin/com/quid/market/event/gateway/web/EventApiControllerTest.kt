package com.quid.market.event.gateway.web

import com.quid.market.event.gateway.web.request.IssueCouponRequest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@Disabled
class EventApiControllerTest{

    @Test
    fun test(){
        val url = "http://localhost:8080/api/events/coupon/issue"
        val restTemplate = RestTemplate()

        val threadPool = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(100)

        for(i in 1..100){
            threadPool.submit {
                try {
                    val httpEntity = HttpEntity<IssueCouponRequest>(IssueCouponRequest(i.toLong(), 1))
                    restTemplate.exchange(url,HttpMethod.POST,httpEntity, String::class.java)
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()
    }
}

