package com.cm.business.notifier;

import com.alibaba.fastjson.JSONObject;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;


@Slf4j
@Component
public class DingtalkNotifier extends AbstractStatusChangeNotifier {

    //    @Autowired
//    private DingTalkRobotClient dingTalkRobotClient;
    @Autowired
    JavaMailSender javaMailSender;
    /**
     * 消息模板
     */
    private static final String template = "<<<%s>>> \n 【服务名】: %s(%s) \n 【状态】: %s(%s) \n 【服务ip】: %s \n 【详情】: %s";

    private String titleAlarm = "系统告警";

    private String titleNotice = "系统通知";

    private String[] ignoreChanges = new String[]{"UNKNOWN:UP", "DOWN:UP", "OFFLINE:UP"};

    public DingtalkNotifier(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected boolean shouldNotify(InstanceEvent event, Instance instance) {
        if (!(event instanceof InstanceStatusChangedEvent)) {
            return false;
        } else {
            InstanceStatusChangedEvent statusChange = (InstanceStatusChangedEvent) event;
            String from = this.getLastStatus(event.getInstance());
            String to = statusChange.getStatusInfo().getStatus();
            return Arrays.binarySearch(this.ignoreChanges, from + ":" + to) < 0 && Arrays.binarySearch(this.ignoreChanges, "*:" + to) < 0 && Arrays.binarySearch(this.ignoreChanges, from + ":*") < 0;
        }
    }


    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {

        return Mono.fromRunnable(() -> {
//            if (!nacosConfigService.getIsopen()){
//                return;
//            }
//
//            String watchapplications = nacosConfigService.getWatchapplications();
//            Boolean flag=watchapplications.contains(instance.getRegistration().getName());
//            if (!flag){
//                return;
//            }
            if (event instanceof InstanceStatusChangedEvent) {
                log.info("Instance {} ({}) is {}", instance.getRegistration().getName(),
                        event.getInstance(),
                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());

                String status = ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus();
                String messageText = null;
                switch (status) {
                    // 健康检查没通过
                    case "DOWN":
                        log.info("发送 健康检查没通过 的通知！");
                        messageText = String
                                .format(template, titleAlarm, instance.getRegistration().getName(), event.getInstance(),
                                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(), "健康检查没通过通知",
                                        instance.getRegistration().getServiceUrl(), JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
                        sendSimpleMail(messageText);
//                        DingtalkUtils.pushInfoToDingding(messageText, "b240b227f5add0fsdfdfsdf5aca64450e71e54721bf08d7ee17114");
                        break;
                    // 服务离线
                    case "OFFLINE":
                        log.info("发送 服务离线 的通知！");
                        messageText = String
                                .format(template, titleAlarm, instance.getRegistration().getName(), event.getInstance(),
                                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(), "服务离线通知",
                                        instance.getRegistration().getServiceUrl(), JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
                        DingtalkUtils.pushInfoToDingding(messageText, "b240b227f5add0fsdfdfsdf5aca64450e71e54721bf08d7ee17114");
                        sendSimpleMail(messageText);
                        break;
                    //服务上线
                    case "UP":
                        log.info("发送 服务上线 的通知！");
                        messageText = String
                                .format(template, titleNotice, instance.getRegistration().getName(), event.getInstance(),
                                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(), "服务上线通知",
                                        instance.getRegistration().getServiceUrl(), JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
                        DingtalkUtils.pushInfoToDingding(messageText, "b240b227f5add0fsdfdfsdf5aca64450e71e54721bf08d7ee17114");
                        System.out.println(messageText);
                        sendSimpleMail(messageText);
                        break;
                    // 服务未知异常
                    case "UNKNOWN":
                        log.info("发送 服务未知异常 的通知！");
                        messageText = String
                                .format(template, titleAlarm, instance.getRegistration().getName(), event.getInstance(),
                                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(), "服务未知异常通知",
                                        instance.getRegistration().getServiceUrl(), JSONObject.toJSONString(instance.getStatusInfo().getDetails()));
                        DingtalkUtils.pushInfoToDingding(messageText, "b240b227f5add0fsdfdfsdf5aca64450e71e54721bf08d7ee17114");
                        sendSimpleMail(messageText);
                        break;
                    default:
                        break;
                }
            } else {
                log.info("Instance {} ({}) {}", instance.getRegistration().getName(), event.getInstance(),
                        event.getType());
            }
        });
    }

    public  void sendSimpleMail(String  messageText) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("服务健康状态报告");
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
        message.setText(messageText);
        // 发送邮件
        javaMailSender.send(message);
    }
}
