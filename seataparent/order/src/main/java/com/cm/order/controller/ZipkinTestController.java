package com.cm.order.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kin")
public class ZipkinTestController {


    @GetMapping("/zipk")
    public String testZikpin(){
        return "ok";
    }
}
