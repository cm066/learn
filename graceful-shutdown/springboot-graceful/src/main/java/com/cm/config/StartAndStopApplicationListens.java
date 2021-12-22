package com.cm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class StartAndStopApplicationListens implements CommandLineRunner, DisposableBean {

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Override
    public void destroy() throws Exception {
        log.info("应用正在关闭，清理相关数据"+new Date());
        int activeCount = threadPoolTaskExecutor.getActiveCount();
        System.out.println(activeCount);
        while (activeCount > 0){
            activeCount = threadPoolTaskExecutor.getActiveCount();
        }
        System.out.println(activeCount);
        log.info("清理工作，已经完成了"+new Date());
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("应用启动成功，预相关加载数据");

    }
}
