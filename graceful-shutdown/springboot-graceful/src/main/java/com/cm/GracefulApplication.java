package com.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GracefulApplication {
    public static void main(String[] args) {
        SpringApplication.run(GracefulApplication.class);
//        SpringApplication ctx = new SpringApplication(GracefulApplication.class);
//        ctx.addListeners(new ApplicationPidFileWriter("/opt/app.pid"));
//        ctx.run();
    }
}
