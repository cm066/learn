package com.cm.log;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysLogService {

    public boolean save(SysLogDB sysLogBO) {
        // 这里就不做具体实现了
        log.info(sysLogBO.getParams());
        log.info(sysLogBO.toString());
        return true;
    }
}