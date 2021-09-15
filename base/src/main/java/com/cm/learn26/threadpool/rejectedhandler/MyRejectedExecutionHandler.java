package com.cm.learn26.threadpool.rejectedhandler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //自定义线程池的拒绝策略
        System.out.println("执行了自定义的线程池的拒绝策略");
    }
}
