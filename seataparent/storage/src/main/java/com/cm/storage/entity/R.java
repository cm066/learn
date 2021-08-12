package com.cm.storage.entity;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回数据类型的类
 */
@Data
public class R {

    private Boolean success;

    private Integer code;
    private String message;

    private Map<String,Object> data = new HashMap<>();

    //私有化，防止别人去new出来
    private R(){}

    //链式编程

    //成功的静态方法
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    //失败的静态方法
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;//当前这个对象，返回
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
