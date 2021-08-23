package com.cm.business;


import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.cm.business.mapper")
@EnableAsync
@EnableAdminServer
public class BusinessApplication {
    public static void main(String[] args) {

        SpringApplication.run(BusinessApplication.class, args);
    }
}
