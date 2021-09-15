package com.cm.bean;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;

import java.util.Properties;

public class App {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", "127.0.0.1:8848");
        properties.setProperty("namespace", "quickStart");
        NamingService naming = null;
        try {
            naming = NamingFactory.createNamingService(properties);
            naming.registerInstance("nacos.test.3", "2.2.2.2", 9999, "DEFAULT");
            System.out.println(naming.getAllInstances("nacos.test.3"));
        } catch (NacosException e) {
            e.printStackTrace();
        }
//        naming.registerInstance("nacos.test.3", "11.11.11.11", 8888, "TEST1");

    }
}
