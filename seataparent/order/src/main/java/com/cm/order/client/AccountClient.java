package com.cm.order.client;


import com.cm.order.client.back.AccountClientBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//,fallback = AccountClientBack.class
@FeignClient(value = "account-service")
@Component
public interface AccountClient {

    @GetMapping("/account/reduce/{userId}/{frozens}")
    boolean updateMOney(@PathVariable Long userId, @PathVariable Integer frozens);

    @GetMapping("/account/reduce1/{userId}/{frozens}")
    boolean updateMOney1(@PathVariable Long userId, @PathVariable Integer frozens);
}
