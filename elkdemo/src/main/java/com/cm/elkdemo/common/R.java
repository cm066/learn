package com.cm.elkdemo.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class R {

    private Boolean success;

    private Integer code;
    private String message;

    private Map<String, Object> data = new HashMap<>();
    public static Map<String, String> xid = new ConcurrentHashMap<>();
    private List<Object> list = new ArrayList<>();

    //私有化，防止别人去new出来
    private R() {
    }

    //链式编程

    //成功的静态方法
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode1.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    //失败的静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode1.ERROR);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;//当前这个对象，返回
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }
    public R list(List list){
        this.setList(list);
        return this;
    }
    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static Map<String, String> getXid() {
        return xid;
    }

    public static void setXid(Map<String, String> xid) {
        R.xid = xid;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
