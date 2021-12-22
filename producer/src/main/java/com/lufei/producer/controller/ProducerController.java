package com.lufei.producer.controller;


import com.lufei.producer.bean.Product;
import io.seata.core.context.RootContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProducerController {


    @GetMapping("test/producer")
    public String testProducer() {
        System.out.println(1);
        System.out.println(RootContext.getXID());
        return "ok";
    }

    @PostMapping("test/postTest")
    public String postTest(@RequestBody Product product){
        System.out.println(product.getPname());
        return product.getPname();
    }
}
