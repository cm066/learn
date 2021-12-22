package com.cm.bean;

public class TerminateBean {

//    @PreDestroy
//    public void preDestroy() throws InterruptedException {
//        System.out.println("进入关闭关闭程序阶段：+"+new Date());
//        TimeUnit.SECONDS.sleep(60);
//        System.out.println("TerminalBean is destroyed,关闭资源完成，要真正的关闭程序了：+"+new Date());
////        while (true){
////            TimeUnit.SECONDS.sleep(2);
////            System.out.println(new Date());
////        }
//    }
}


/**
 apiVersion: v1
 kind: Pod
 metadata:
 name: abcdocker
 labels:
 name: abcdocker
 spec:
 containers:
 - name: abcdocker
 image: nginx
 ports:
 - containerPort: 80
 lifecycle:
 preStop:
 exec:
 command:
 command: ["/bin/sh", "-c",
 "curl -X DELETE http://192.168.224.1:8848/nacos/v1/ns/instance?serviceName=graceful-service&ip=$MY_POD_IP&port=3333
 ;sleep 30s"]
 env:
 - name: MY_POD_IP
 valueFrom:
 fieldRef:
 fieldPath: status.podIP
 */