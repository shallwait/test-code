package com.sewell.config;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.sewell.event.PongEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.concurrent.Executors;

@Slf4j
@Configuration
public class PongConfig {

    @Bean
    public RingBuffer<PongEvent> ringBuffer() {
        int bufferSize = 1024;
        Disruptor<PongEvent> disruptor = new Disruptor<>(PongEvent::new, bufferSize, Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> log.info("时间[{}],读取文件[{}],内容[{}]", Instant.ofEpochMilli(System.currentTimeMillis()), event.getName(), event.getText()));
        disruptor.start();
        return disruptor.getRingBuffer();
    }
}
