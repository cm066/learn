package com.cm.proxy.proxy1;

import java.lang.reflect.Method;

public class Invocation {

    private Object target;

    private Method method;

    private Object[] args;
    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    /**
     * 执行目标对象的方法
     */
    public Object process() throws Exception{
        return method.invoke(target,args);
    }
}
