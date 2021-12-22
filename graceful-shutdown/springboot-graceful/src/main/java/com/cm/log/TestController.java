package com.cm.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/sys")
@Slf4j
public class TestController {

    @SysLog("测试")
    @GetMapping("/test/{name}")
    public String test(@PathVariable String name){
        String userNo = UUID.randomUUID().toString();
        MDC.put("userId", userNo);
        log.info(userNo);
        log.info("hhhhhhhh");
        return name;
    }
}