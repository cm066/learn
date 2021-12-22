package com.cm.proxy.proxy1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InterceptorChain {

    private List<Interceptor> interceptorList = new ArrayList<>();
    /**
     * 插入所有的拦截器
     */
    public Object pluginAll(Object target){
        for (Interceptor interceptor : interceptorList) {
            target = interceptor.plugin(target);
            System.out.println(target.getClass());
        }
        return target;
    }

    public void addInterceptor(Interceptor interceptor){
        interceptorList.add(interceptor);
    }

    /**
     * 返回一个不可修改的集合
     * @return
     */
    public List<Interceptor> getInterceptorList(){
        return Collections.unmodifiableList(interceptorList);
    }
}
