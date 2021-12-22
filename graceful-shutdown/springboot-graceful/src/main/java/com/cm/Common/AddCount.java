package com.cm.Common;

import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

public class AddCount {
    public static AtomicInteger in = new AtomicInteger(0);
    public static AtomicInteger mid = new AtomicInteger(0);
    public static AtomicInteger com = new AtomicInteger(0);

    @Configuration
    public static class ShutDownConfig {

    //    @Bean
    //    public TerminateBean getTerminateBean() {
    //
    //        return new TerminateBean();
    //    }
    }
}
