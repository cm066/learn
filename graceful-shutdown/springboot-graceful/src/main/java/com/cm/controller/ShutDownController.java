package com.cm.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.registry.NacosRegistration;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ShutDownController {
    protected final transient Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;
    @Autowired
    NacosRegistration nacosRegistration;

    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;

    @Autowired
//    NacosRegistration nacosRegistration;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        List<ServiceInstance> instances = client.getInstances("graceful-service");
        System.out.println(instances);
        return "Hello World";
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
     *
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
    }

}
