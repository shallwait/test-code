package com.sewell.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
class PongServiceSpec extends Specification {

    @Autowired
    PongService pongService

    def setupSpec() {
        println ">>>>>>   setupSpec"
    }

    def "test readFile"() {
        expect:
        pongService.readFile()
    }
}
