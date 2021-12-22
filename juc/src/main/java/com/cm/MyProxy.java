package com.cm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy {
    public interface HelloService{
        void sayHello();
    }

    static class HelloServiceImpl implements HelloService{

        @Override
        public void sayHello() {
            System.out.println("sayHello....");
        }
    }

    static class HWInvocationHandler implements InvocationHandler {
        private Object target;

        public HWInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("代理之前");
            Object invoke = method.invoke(target, args);
            System.out.println("代理之后");
            return invoke;
        }

        public static Object wrap(Object target){
            return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),new HWInvocationHandler(target));
        }

    }

    public static void main(String[] args) {
        HelloServiceImpl target = new HelloServiceImpl();
        HWInvocationHandler hwInvocationHandler = new HWInvocationHandler(target);
        HelloService helloService1 = (HelloService) Proxy.newProxyInstance(hwInvocationHandler.getClass().getClassLoader(),
                target.getClass().getInterfaces(),hwInvocationHandler);
        HelloService helloService = (HelloService) HWInvocationHandler.wrap(new HelloServiceImpl());
        helloService.sayHello();
    }
}
