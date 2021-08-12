package com.cm.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.cm.storage.mapper")
@EnableFeignClients
public class StorageApplication {

    public static void main(String[] args) {

        SpringApplication.run(StorageApplication.class, args);
    }
}
