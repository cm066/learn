package com.cm.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 */
@ConfigurationProperties(prefix = CaptchaProperties.CAPTCHA_PREFIX)
@Component
public class CaptchaProperties {

    public static final String CAPTCHA_PREFIX = "sao.captcha";

    private long expiresTime = 120000;

    private List<VerificationCodePath> paths;


    public List<VerificationCodePath> getPaths() {
        return paths;
    }

    public void setPaths(List<VerificationCodePath> paths) {
        this.paths = paths;
    }

    public long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }

    @Override
    public String toString() {
        return "CaptchaProperties{" +
                "expiresTime=" + expiresTime +
                ", paths=" + paths +
                '}';
    }

    public static class VerificationCodePath {
        private String path;

        private boolean require = true;

        //预留字段 策略(目前默认请求头) 后期可以配置
        private String strategy = "header";

        //参数名称
        private String name = "ver-code";

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public boolean isRequire() {
            return require;
        }

        public void setRequire(boolean require) {
            this.require = require;
        }

        public String getStrategy() {
            return strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "VerificationCodePath{" +
                    "path='" + path + '\'' +
                    ", require=" + require +
                    ", strategy='" + strategy + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
