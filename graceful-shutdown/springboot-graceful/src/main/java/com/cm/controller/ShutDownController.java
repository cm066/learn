package com.cm.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.registry.NacosRegistration;
import com.alibaba.nacos.api.exception.NacosException;
import com.cm.bean.CaptchaProperties;
import com.cm.bean.ListBean;
import com.cm.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;



@RestController
@Slf4j
public class ShutDownController {
    @Autowired
    private DiscoveryClient client;
    @Autowired
    NacosRegistration nacosRegistration;

    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;
    @Autowired
    TestService testService;

    @Autowired
    ListBean listBean;

    @Autowired
    CaptchaProperties captchaProperties;
    //记录特定日志的声明
    private final Logger businessLog = LoggerFactory.getLogger("businessLog");


    @GetMapping("/test10")
    public String test10(ServerWebExchange exchange){
        String serverName = exchange.getRequest().getURI().toString();
        System.out.println(serverName);
        System.out.println(captchaProperties);
        return listBean.getName();
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        List<ServiceInstance> instances = client.getInstances("graceful-service");
        //日志存储
        log.info("修改了配送地址");
        System.out.println(instances);
        return "Hello World";
    }

    @GetMapping("/test")
    public String test() throws InterruptedException {
        testService.test();
        return "ok";
    }

//    /**
//     * Nacos 服务列表端的服务实例主动下线
//     * @return
//     */
//    @RequestMapping("/api/nacos/deRegisterServer")
//    @GetMapping
//    public boolean deRegisterServer()
//    {
//        Instance instance = new Instance();
//        instance.setIp("127.0.0.1");
//        instance.setPort(3333);
////        instance.setPort(8848);
////        instance.setClusterName(nacosDiscoveryProperties.getClusterName());
//        instance.setServiceName("graceful-service");
//        try {
//            nacosRegistration.getNacosNamingService().deregisterInstance("graceful-service",instance);
//            return true;
//        } catch (NacosException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }


    /**
     * 服务注销接口
     * @return
     */
    @GetMapping(value = "/api/nacos/deregister")
    public String deregisterInstance() {
        String serviceName = nacosDiscoveryProperties.getService();
        String groupName = nacosDiscoveryProperties.getGroup();
        String clusterName = nacosDiscoveryProperties.getClusterName();
        String ip = nacosDiscoveryProperties.getIp();
        int port = nacosDiscoveryProperties.getPort();

        log.info("deregister from nacos, serviceName:{}, groupName:{}, clusterName:{}, ip:{}, port:{}", serviceName, groupName, clusterName, ip, port);
        try {
            nacosRegistration.getNacosNamingService().deregisterInstance(serviceName, groupName, ip, port, clusterName);
        } catch (NacosException e) {
            log.error("deregister from nacos error", e);
            return "error";
        }
        return "success";
//        lifecycle:
//        postStart:
//        exec:
        //curl -X GET http://192.168.224.121:32664/api/nacos/deregister
//        command: ["/bin/sh", "-c", "crul http:localhot:3333/hello"]
        //crul http:localhot:3333/api/nacos/deregister
        //curl -X DELETE http://192.168.224.1:8848/nacos/v1/ns/instance?serviceName=graceful-service&ip=$MY_POD_IP&port=3333; sleep 3000
        //curl -X GET http://192.168.224.121:32664/api/nacos/deregister;
    }

}
