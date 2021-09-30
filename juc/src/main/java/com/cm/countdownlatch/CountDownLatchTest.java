package com.cm.countdownlatch;

import java.util.concurrent.*;

public class CountDownLatchTest {
    private static final int CORE_POOL_SIZE = 4;
    private static final int MAX_POOL_SIZE = 12;
    private static final long KEEP_ALIVE_TIME = 5L;
    private final static int QUEUE_SIZE = 1600;

    /**
     * 线程池的拒绝策略，自定义可以实现RejectedExecutionHandler接口，在里面来实现自己的业务逻辑
     * 默认的四种策略
     * ThreadPoolExecutor.AbortPolicy：默认拒绝处理策略，丢弃任务并抛出RejectedExecutionException异常。
     *
     * ThreadPoolExecutor.DiscardPolicy：丢弃新来的任务，但是不抛出异常。
     *
     * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列头部（最旧的）的任务，然后重新尝试执行程序（如果再次失败，重复此过程）。
     *
     * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务。
     */
    protected final static ExecutorService THREAD_POOL = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(QUEUE_SIZE));

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        THREAD_POOL.execute(()->{
            System.out.println("第一个线程");
            countDownLatch.countDown();
        });
        countDownLatch.await(1,TimeUnit.SECONDS);
        System.out.println("主线程："+Thread.currentThread().getName());
    }

}
