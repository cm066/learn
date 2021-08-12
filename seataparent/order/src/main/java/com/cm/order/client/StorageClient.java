package com.cm.order.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("storage-service")
@Component

public interface StorageClient {

    @GetMapping("/ticket/toTicket/{id}/{frozens}")
    boolean toTicket(@PathVariable Long id, @PathVariable Integer frozens);
}
