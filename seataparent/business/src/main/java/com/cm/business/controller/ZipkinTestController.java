package com.cm.business.controller;


import com.cm.business.client.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZipkinTestController {

    @Autowired
    OrderClient orderClient;


    @GetMapping("test1")
    public String tets1(){
        String s = orderClient.testZikpin();
        return s;
    }
}
