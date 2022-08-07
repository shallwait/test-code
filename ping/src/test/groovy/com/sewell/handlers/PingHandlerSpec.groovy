package com.sewell.handlers

import cn.hutool.core.io.FileUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
class PingHandlerSpec extends Specification {

    @Autowired
    PingHandler pingHandler

    def "test writeHello"() {
        when:
        File[] ls = FileUtil.ls("D:\\test\\");
        pingHandler.writeHello(null);
        File[] ls2 = FileUtil.ls("D:\\test\\");

        then:
        ls2.length - ls.length == 1;
    }
}
