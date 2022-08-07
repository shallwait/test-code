package com.sewell.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.lmax.disruptor.RingBuffer;
import com.sewell.event.PongEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PongService {

    private long current = 0L;

    @Value("${read.size}")
    private int size;

    @Value("${read.path}")
    private String path;

    private final String suffix = ".txt";

    @Autowired
    private RingBuffer<PongEvent> ringBuffer;

    @Scheduled(fixedRate = 500)
    public void readFile() {
        File[] ls = FileUtil.ls(path);
        Map<Long, File> fileMap = Arrays.stream(ls).parallel()
                .collect(Collectors.toMap(s -> Long.valueOf(StrUtil.removeSuffix(s.getName(), suffix)), Function.identity()));
        boolean b = true;
        while (b) {
            b = publishEvents(fileMap);
        }
    }

    private boolean publishEvents(Map<Long, File> fileMap) {
        List<Long> list = fileMap.keySet().stream().filter(l -> l > current).sorted().limit(size).collect(Collectors.toList());
        if (list.size() == 0) {
            return false;
        }
        for (Long l : list) {
            ringBuffer.publishEvent((event, sequence) -> {
                event.setName(fileMap.get(l).getName());
                event.setText(FileUtil.readUtf8String(fileMap.get(l)));
            });
        }
        current = list.get(list.size() - 1);
        return true;
    }

}
