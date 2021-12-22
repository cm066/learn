package com.cm.kafka;

import java.util.concurrent.CountDownLatch;

public class Test2 {
//   public static final ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
//   public static final ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
   public static final CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
            ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
            threadLocal1.set(1);
            threadLocal2.set("3333");
            test(threadLocal1,threadLocal2);
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
//        String s = threadLocal2.get();
//        if (s == null){
//            s = "bb";
//        }
        System.out.println(111);
    }

    public static void test(ThreadLocal<Integer> threadLocal1, ThreadLocal<String> threadLocal2){
        threadLocal1.get();
        System.out.println(threadLocal1.get()+threadLocal2.get());
        threadLocal1.remove();
        threadLocal2.remove();
        System.out.println(threadLocal1.get()+threadLocal2.get());
    }
}
