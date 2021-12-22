package com.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableDiscoveryClient
public class GracefulApplication {
    public static void main(String[] args) {
        SpringApplication.run(GracefulApplication.class);
//        SpringApplication ctx = new SpringApplication(GracefulApplication.class);
//        ctx.addListeners(new ApplicationPidFileWriter("/opt/app.pid"));
//        ctx.run();
    }
}
