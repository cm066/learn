package com.cm.learn26.threadpool.test;


import com.cm.learn26.threadpool.rejectedhandler.MyRejectedExecutionHandler;

import java.util.concurrent.*;

/**
 * 线程池
 * https://tech.meituan.com/2020/04/02/java-pooling-pratice-in-meituan.html
 * 如何去监控线程池的状态，并且根据可以动态的去去修该线程池的配置？
 * 为什么要去监控线程池的状态并且要动态的去修改它？
 * 1、因为线程池的配置参数没有具体的标准，而且在不同的场景下或者是不到类型下cup密集型或者是io密集型下，
 * 参数的设置是有很大的影响，并且要根据实际的情况来动态的更改参数，
 * 可以利用ThreadPoolExecutor 的 get和set方法来修改这些参数
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {

        /**
         * this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
         *              threadFactory, defaultHandler)
         *
         *              int corePoolSize,
         *                               int maximumPoolSize,
         *                               long keepAliveTime,
         *                               TimeUnit unit,
         *                               BlockingQueue<Runnable> workQueue,
         *                               ThreadFactory threadFactory
         */
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                5,
                10,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new MyRejectedExecutionHandler()
        );
        executorService.execute(()->{
            System.out.println(1);
        });
    }
}
