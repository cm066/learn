package com.cm.proxy.proxy0;

public class LogInterceptor implements Interceptor {
    @Override
    public void intercept() {
        System.out.println("------插入前置通知代码-------------");
    }
}
