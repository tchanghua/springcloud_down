package com.lagou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmailServiceApplication8082 {
    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication8082.class,args);
    }
}
