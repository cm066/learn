package com.cm.order.controller;

import com.cm.order.entity.Orders;
import com.cm.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/reduce1/{productId}/{productNum}")
    public boolean insertOrder(@PathVariable Long productId, @PathVariable Integer productNum) {
        Orders order = new Orders();
        order.setUserId(989898L);
        order.setProductId(productId);
        order.setStatus(0);
        order.setProductNum(productNum);
        boolean b = orderService.insertOrder(order);
        return b;
    }

    @GetMapping("/reduce2/{id}/{userId}/{moneyFrozens}/{ticketFrozens}")
    public boolean insertOrder1(@PathVariable Long id, @PathVariable Long userId,
                                @PathVariable Integer moneyFrozens, @PathVariable Integer ticketFrozens) {
        boolean b = orderService.toOrder(id, userId, moneyFrozens, ticketFrozens);
        return b;
    }

    @GetMapping("get1")
    public String get1() {
        log.info("这里是新日志，日志XXX:{}", "abc");
        int i = 0;
//        int x = 3 / i;
        return "ok";
    }
}
