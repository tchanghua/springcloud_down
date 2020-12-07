package com.lagou.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component //让容器扫码到，就等同于注册了
public class RightFilter implements GlobalFilter, Ordered {

    // exchange 封装了request和response对象的上下文
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        URI uri = request.getURI();

        List<HttpCookie> token = request.getCookies().get("token");

        //System.out.println(token);
        //System.out.println(uri.toString());
        //System.out.println(request.getPath());

        return chain.filter(exchange);
    }

    //返回值表示当前过滤器的顺序（优先级），数值越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
