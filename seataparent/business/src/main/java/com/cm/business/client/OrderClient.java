package com.cm.business.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//,fallback = OrderClientBack.class
@FeignClient(value = "order-service")
@Component
public interface OrderClient {

    @GetMapping("/order/reduce1/{productId}/{productNum}")
    boolean insertOrder(@PathVariable Long productId, @PathVariable Integer productNum);

    @GetMapping("/kin/zipk")
    public String testZikpin();
}
