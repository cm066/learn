package com.cm.business.client;


import com.cm.business.client.back.StorageClientBack;
import com.cm.business.entity.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//,fallback = StorageClientBack.class
@FeignClient(value = "storage-service", fallback = StorageClientBack.class)
@Component
public interface StorageClient {

    @GetMapping("/storage/reduce1/{productId}/{currentUse}")
    boolean redcuce1(@PathVariable Long productId, @PathVariable int currentUse);

    @GetMapping("/storage/reduce2/{productId}/{currentUse}")
    R redcuce2(@PathVariable Long productId, @PathVariable int currentUse);
}
