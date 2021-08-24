package com.cm.dockerdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.LockSupport;

@RestController
public class DockerControoler {

    @GetMapping("/dockertest")
    public String dockerTest(){
        System.out.println("dockerTest");
        LockSupport.park();
        return "ok";
    }
}
