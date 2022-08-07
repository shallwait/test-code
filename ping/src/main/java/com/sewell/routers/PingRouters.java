package com.sewell.routers;

import com.sewell.handlers.PingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class PingRouters {
    @Bean
    RouterFunction<ServerResponse> pingRouter(PingHandler handler) {
        return nest(
                path("/hello"),
                route(GET("/write"), handler::writeHello));
    }
}
