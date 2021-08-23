package com.cm.business.controller;


import com.cm.business.entity.Integrals;
import com.cm.business.entity.Msg;
import com.cm.business.entity.R;
import com.cm.business.service.BuisnessService;
import com.cm.business.service.IntegralsService;
import io.seata.core.exception.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("bui")
public class BuisnessController {

    @Autowired
    private BuisnessService buisnessService;

    @Autowired
    private IntegralsService integralsService;

    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping("toOrder/{productId}/{currentUse}")
    public String toOrder(@PathVariable Long productId, @PathVariable int currentUse){
        Msg msg = new Msg();
        msg.setProductId(productId);
        msg.setCurrentUse(currentUse);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        msg.setUuid(uuid);
        buisnessService.toSendMsg(msg);
//        int i = 10 / 0;
        return "ok";
    }

    @GetMapping("/insert")
    public String  insert(){
        Integrals in = new Integrals();
        in.setCount(100);
        in.setUserId(989898L);
//        integralsService.toInsert();
        return "ok";
    }

    @GetMapping("/insert1")
    public R insert1(){
        Integrals in = new Integrals();
        in.setCount(100);
        in.setUserId(989898L);
//        integralsService.toInsert();
        return R.ok();
    }

    @GetMapping("/toOrder1/{productId}/{currentUse}")
    public R toOrder1(@PathVariable Long productId, @PathVariable int currentUse) throws TransactionException {
        buisnessService.toOrder(productId,currentUse);
        return R.ok();
    }

    @GetMapping("test1")
    public String sendSimpleMail(){
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("这是一封测试邮件");
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom("1446525549@qq.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        // message.setTo("10*****16@qq.com","12****32*qq.com");
        message.setTo("934997392@qq.com");
//        // 设置邮件抄送人，可以有多个抄送人
//        message.setCc("12****32*qq.com");
//        // 设置隐秘抄送人，可以有多个
//        message.setBcc("7******9@qq.com");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText("这是测试邮件的正文");
        // 发送邮件
        javaMailSender.send(message);
        return "ok";
    }
}
