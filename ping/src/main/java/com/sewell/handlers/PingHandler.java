package com.sewell.handlers;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import com.sewell.constant.PingConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@Component
public class PingHandler {
    @Value("${write.path}")
    private String path;

    public Mono<ServerResponse> writeHello(ServerRequest request) {
        Supplier<String> text = () -> PingConstant.HELLO;
        Supplier<String> name = () -> path + System.currentTimeMillis() + PingConstant.TXT;

        FileWriter.create(FileUtil.file(name.get())).write(text.get());
        return ServerResponse.ok().build();
    }
}
