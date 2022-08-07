package com.sewell.scheduled

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
class PingScheduledSpec extends Specification{

    @Autowired
    private PingScheduled pingScheduled;

    def "test writeHello"() {
        expect:
        pingScheduled.writeHello()
    }
}
