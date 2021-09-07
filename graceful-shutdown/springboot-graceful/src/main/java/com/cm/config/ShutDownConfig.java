package com.cm.config;

import com.cm.bean.TerminateBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShutDownConfig {

    @Bean
    public TerminateBean getTerminateBean() {
        return new TerminateBean();
    }
}
