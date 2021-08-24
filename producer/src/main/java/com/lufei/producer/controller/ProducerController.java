package com.lufei.producer.controller;


import io.seata.core.context.RootContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProducerController {


    @GetMapping("test/producer")
    public String testProducer() {
        System.out.println(1);
        System.out.println(RootContext.getXID());
        return "ok";
    }
}
