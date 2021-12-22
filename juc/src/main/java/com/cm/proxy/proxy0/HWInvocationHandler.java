package com.cm.proxy.proxy0;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class HWInvocationHandler implements InvocationHandler {
    private Object target;

    private List<Interceptor> interceptorList = new ArrayList<>();

    public HWInvocationHandler(Object target,List<Interceptor> interceptorList) {
        this.target = target;
        this.interceptorList = interceptorList;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //可以在执行这个方法之前执行拦截器
        for (Interceptor interceptor : interceptorList) {
            interceptor.intercept();
        }
        //执行被代理的方法
        Object invoke = method.invoke(target, args);
        //代理方法执行完成以后要执行的方法
        System.out.println("处理完了目标方法");
        return invoke;
    }
    public static Object wrap(Object target,List<Interceptor> interceptorList){
        HWInvocationHandler targetProxy = new HWInvocationHandler(target, interceptorList);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),targetProxy);
    }
}
