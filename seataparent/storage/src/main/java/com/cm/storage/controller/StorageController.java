package com.cm.storage.controller;

import com.cm.storage.entity.R;
import com.cm.storage.entity.Storage;
import com.cm.storage.mapper.StorageMapper;
import com.cm.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("storage")
public class StorageController {

    @Autowired
    StorageMapper storageMapper;
    @Autowired
    private StorageService storageService;

    @GetMapping("/reduce")
    public String redcuce() {
        Storage storage = new Storage();
        storage.setTotal(100);
        storage.setProductId(1L);
        storage.setUsed(11);
        storageMapper.insert(storage);
        return "ok";
    }

    @GetMapping("/reduce1/{productId}/{currentUse}")
    public boolean redcuce1(@PathVariable Long productId, @PathVariable int currentUse) {

        boolean b = storageService.changeUse(productId, currentUse);
        return b;
    }

    @GetMapping("/reduce2/{productId}/{currentUse}")
    public R redcuce2(@PathVariable Long productId, @PathVariable int currentUse) {

        boolean b = storageService.changeUse(productId, currentUse);
        return R.ok();
    }

}
