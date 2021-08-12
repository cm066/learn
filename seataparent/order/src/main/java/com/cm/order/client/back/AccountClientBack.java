package com.cm.order.client.back;


import com.cm.order.client.AccountClient;

import org.springframework.stereotype.Component;

//@Component
public class AccountClientBack implements AccountClient {


    @Override
    public boolean updateMOney(Long userId, Integer frozens) {
        System.out.println("远程执行Count服务失败，执行熔断方法");
        return false;
    }

    @Override
    public boolean updateMOney1(Long userId, Integer frozens) {
        return false;
    }
}
