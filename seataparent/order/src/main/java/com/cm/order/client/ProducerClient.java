package com.cm.order.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "producer")
public interface ProducerClient {

    @GetMapping("test/producer")
    String testProducer();
}
