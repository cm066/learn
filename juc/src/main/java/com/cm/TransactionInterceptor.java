package com.cm;

import com.cm.proxy.proxy0.Interceptor;

public class TransactionInterceptor implements Interceptor {
    @Override
    public void intercept() {
        System.out.println("------插入后置处理代码-------------");
    }
}
