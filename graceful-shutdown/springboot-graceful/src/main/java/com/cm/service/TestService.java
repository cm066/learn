package com.cm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestService {

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public String test() throws InterruptedException {

        threadPoolTaskExecutor.submit(()->{
            try {
                TimeUnit.SECONDS.sleep(120);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return "ok";
    }
}
