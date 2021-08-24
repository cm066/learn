package com.cm.business.config;


import com.cm.business.entity.R;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class AsyncAspect {
    @Pointcut("execution(public * com.cm.business.service.impl.*.*(..))")
    public void async() {
    }

    //后置异常通知
    @AfterThrowing("async()")
    public void throwss(JoinPoint jp) {
        System.out.println("111111111");
        System.err.println(Thread.currentThread().getId());
        System.out.println("方法异常时执行.....");
        System.err.println(R.xid.get(Thread.currentThread().getId() + ""));
        try {
            GlobalTransactionContext.reload(R.xid.get("1")).rollback();
        } catch (TransactionException e) {
            e.printStackTrace();
        }
    }

//    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
//    @After("async()")
//    public void after(JoinPoint jp) {
//        System.out.println("异步方法执行完");
//        System.out.println(R.xid.get("1"));
//        try {
//            GlobalTransactionContext.reload(R.xid.get("1")).rollback();
//        } catch (TransactionException e) {
//            e.printStackTrace();
//        }
//
//    }
}
