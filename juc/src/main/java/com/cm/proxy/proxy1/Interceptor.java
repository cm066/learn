package com.cm.proxy.proxy1;

public interface Interceptor {
    /**
     * 具体拦截处理
     */
    Object intercept(Invocation invocation) throws Exception;

    Object plugin(Object target);
}
