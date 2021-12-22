package com.cm.proxy.proxy1;

public class Test {
    public static void main(String[] args) {

        /**
         * 责任链模式加动态代理，实现的多个拦截器，简单的来说，就是实现的细节包装起来
         */
        HelloService target = new HelloServiceImpl();
        Interceptor trans = new TransactionInterceptor();
        Interceptor longin = new LogInterceptor();
        InterceptorChain interceptorChain = new InterceptorChain();
        interceptorChain.addInterceptor(trans);
        interceptorChain.addInterceptor(longin);
        target = (HelloService) interceptorChain.pluginAll(target);
        target.sayHello();
        System.out.println(target.getClass());
        /**
         * 多个拦截器
         */
//        HelloService target = new HelloServiceImpl();
//        Interceptor transactionInterceptor = new TransactionInterceptor();
//        HelloService target2 = (HelloService) transactionInterceptor.plugin(target);
//        LogInterceptor logInterceptor = new LogInterceptor();
//        target = (HelloService)logInterceptor.plugin(target2);
//        target.sayHello();

        /**
         * 一个拦截器
         */
//        HelloService target = new HelloServiceImpl();
//        Interceptor interceptor = new TransactionInterceptor();
//        target = (HelloService) interceptor.plugin(target);
//        target.sayHello();
//        HelloService targetProxy = (HelloService) HWInvocationHandler.wrap(target,interceptor);
//        targetProxy.sayHello();
    }
}
