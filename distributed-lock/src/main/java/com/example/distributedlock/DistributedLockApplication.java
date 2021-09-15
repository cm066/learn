package com.example.distributedlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * 目前的问题就是对于分布式锁的了解还不够深入
 * 对于解决的办法也是不是很了解
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DistributedLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedLockApplication.class, args);
    }

}