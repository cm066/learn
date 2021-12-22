package com.cm.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Administrator
 */
@Configuration
public class ApiConfig extends WebMvcConfigurationSupport {

    @Autowired
    MyInterceptor myInterceptor;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("/test1").excludePathPatterns("/test4");
        super.addInterceptors(registry);
    }
}
