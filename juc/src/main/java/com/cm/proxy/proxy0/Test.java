package com.cm.proxy.proxy0;

import com.cm.TransactionInterceptor;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Interceptor> interceptorList = new ArrayList<>();
        interceptorList.add(new LogInterceptor());
        interceptorList.add(new TransactionInterceptor());
        HelloService target = new HelloServiceImpl();
        HelloService targetProxy = (HelloService) HWInvocationHandler.wrap(target,interceptorList);
        targetProxy.sayHello();
    }
}
