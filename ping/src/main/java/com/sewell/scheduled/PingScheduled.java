package com.sewell.scheduled;

import com.sewell.handlers.PingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PingScheduled {

    @Autowired
    private PingHandler pingHandler;

    @Scheduled(cron = "0/1 * * * * ?")
    public void writeHello() {
        pingHandler.writeHello(null);
    }
}
