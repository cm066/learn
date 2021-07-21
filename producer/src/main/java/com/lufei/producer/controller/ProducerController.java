package com.lufei.producer.controller;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProducerController {


    @GetMapping("test/producer")
    public String testProducer(){
        System.out.println(1);
        return "ok";
    }
}
