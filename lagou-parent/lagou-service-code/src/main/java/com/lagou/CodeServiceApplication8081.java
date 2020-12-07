package com.lagou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
public class CodeServiceApplication8081 {

    public static void main(String[] args) {
        SpringApplication.run(CodeServiceApplication8081.class,args);
    }
}
