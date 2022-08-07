package com.sewell.config

import com.lmax.disruptor.RingBuffer
import com.sewell.event.PongEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
class PongConfigSpec extends Specification {

    @Autowired
    PongConfig pongConfig

    def setupSpec() {
        println ">>>>>>   setupSpec"
    }

    def "test ringBuffer"() {
        expect:
        RingBuffer<PongEvent> ringBuffer = pongConfig.ringBuffer()
        long sequence = ringBuffer.next()
        PongEvent event = ringBuffer.get(sequence)
        event.setName("name")
        event.setText("text")
        ringBuffer.publish(sequence)
    }
}
