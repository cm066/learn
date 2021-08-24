package com.cm.storage.service.impl;

import com.cm.storage.mapper.StorageMapper;
import com.cm.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageMapper storageMapper;


    @Override
    public boolean changeUse(long productId, int currentUse) {
//        int i1 = 10 /0;
        int i = storageMapper.updateUsed(productId, currentUse);

        return i > 0;
    }
}
