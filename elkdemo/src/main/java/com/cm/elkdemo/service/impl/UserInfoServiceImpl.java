package com.cm.elkdemo.service.impl;

import com.cm.elkdemo.entity.UserInfo;
import com.cm.elkdemo.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Service
@CacheConfig(cacheNames = "caffeineCacheManager")
public class UserInfoServiceImpl implements UserInfoService {

    private Map<Integer,UserInfo> map = new ConcurrentHashMap<>();

    @Override
    @CachePut(key = "#userInfo.id") //这个注解是每次都会执行真实的方法
    public void addUserInfo(UserInfo userInfo) {
        log.info("create");
        map.put(userInfo.getId(),userInfo);
    }

    @Override
    @Cacheable(key = "#id") //这个注解的作用是，根据这个key去查询缓存，若缓存里面没有，在执行方法
    public UserInfo getByName(Integer id) {
        log.info("缓存里面没有获取到数据");
        return map.get(id);
    }

    @Override

    public UserInfo updateUserInfo(UserInfo userInfo) {
        return null;
    }

    @Override
    @CacheEvict(key = "#id") //这个注解是删除缓存，根据key去删除缓存
    public void deleteById(Integer id) {
        log.info("delete");
        map.remove(id);
    }
}
