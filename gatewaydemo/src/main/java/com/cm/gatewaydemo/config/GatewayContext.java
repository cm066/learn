package com.cm.gatewaydemo.config;


public class GatewayContext {
    public static final String CACHE_GATEWAY_CONTEXT = "cache_gateway_context";
    private              String requestBody;
    private              String path;

    public GatewayContext() {
    }

    public GatewayContext(String requestBody, String path) {
        this.requestBody = requestBody;
        this.path = path;
    }

    public static String getCacheGatewayContext() {
        return CACHE_GATEWAY_CONTEXT;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
