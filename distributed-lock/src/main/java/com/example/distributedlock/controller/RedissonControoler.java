package com.example.distributedlock.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class RedissonControoler {

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    ThreadPoolTaskExecutor executor;
//    @Autowired
//    RedissonClient redissonClient;
//    @GetMapping("test")
//    public String testRedisson(){
//        //获取分布式锁，只要锁的名字一样，就是同一把锁
//        RLock lock = redissonClient.getLock("lock");
//        //加锁（阻塞等待），默认过期时间是30秒
//        lock.lock();
//        try{
//            //如果业务执行过长，Redisson会自动给锁续期
//            Thread.sleep(5000);
//            System.out.println("加锁成功，执行业务逻辑"+"->"+Thread.currentThread().getId());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            //解锁，如果业务执行完成，就不会继续续期，即使没有手动释放锁，在30秒过后，也会释放锁
//            lock.unlock();
//        }
//        return "Hello Redisson!";
//    }
    @GetMapping("/test")
    public String testRedisson() throws InterruptedException {
//        redisTemplate.opsForValue().set("1","1");
        executor.submit(()->{
            System.out.println(88888);
            System.out.println(executor.getActiveCount()+"->"+executor.getPoolSize());
            try {
                TimeUnit.SECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.submit(()->{
            System.out.println(88888);
            System.out.println(executor.getActiveCount()+"->"+executor.getPoolSize());
            try {
                TimeUnit.SECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        String result = executor.getCorePoolSize()+"->"+executor.getMaxPoolSize()+"->"
                +executor.getPoolSize();
        System.out.println(result);
        TimeUnit.SECONDS.sleep(2000);
        return result;
    }

}
