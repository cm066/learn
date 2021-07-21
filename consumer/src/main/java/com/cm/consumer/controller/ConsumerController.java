package com.cm.consumer.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @GetMapping("test/producer")
    public String testProducer(){
        System.out.println(1);
        return "ok1";
    }
}
