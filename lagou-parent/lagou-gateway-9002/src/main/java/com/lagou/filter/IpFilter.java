package com.lagou.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component //让容器扫码到，就等同于注册了
public class IpFilter implements GlobalFilter, Ordered {
    private Map<String,Integer> ipMap=new HashMap<>();

    @Scheduled(cron = "0 */1 * * * ?")
    public void setIpMap() {
        //每隔一分钟清空 ip统计记录
        this.ipMap = new HashMap<>();
        System.out.println("定时任务 每隔一分钟执行！");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path =request.getPath().toString();
        System.out.println(path);
        if(path.contains("/api/user/register")){//注册接口
            String ip = request.getRemoteAddress().getHostString();
            if (ipMap.containsKey(ip)){
                Integer count = ipMap.get(ip);
                if (count >4){//一分钟大于5次  拒绝请求
                    //拒绝访问，返回
                    response.setStatusCode(HttpStatus.UNAUTHORIZED); //状态码
                    String data ="request be denied!";//
                    DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
                    return response.writeWith(Mono.just(wrap));
                }
                ipMap.put(ip,count+1);
            }else {
                ipMap.put(ip,1);
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
