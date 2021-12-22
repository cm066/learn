package com.cm.gatewaydemo.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.web.server.ServerWebExchange;

import java.util.Objects;

public class ParamBodyUtil {

    public static String getPath(ServerWebExchange exchange){

        Route gatewayRoute = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String path;
        if(Objects.isNull(gatewayRoute)){
            //兼容CorsConfiguration优先级高于路由拦截器
            return exchange.getRequest().getPath().pathWithinApplication().value();
        }
        String routePrefix  = gatewayRoute.getUri().toString().replace("lb://", "");
        //openapi的路由规则没有配置filter
//        return (StringUtils.isNotBlank(routePrefix) && !routePrefix.contains("openapi") ? "/" + routePrefix : "") +
//                exchange.getRequest().getPath().pathWithinApplication().value();
        //todo：线上的openapi也要配置filter
        return (StringUtils.isNotBlank(routePrefix) ? "/" + routePrefix : "") +
                exchange.getRequest().getPath().pathWithinApplication().value();
    }

    public static DataBuffer getDataBuffer(DataBufferFactory bufferFactory, byte[] bytes) {
        return bufferFactory.allocateBuffer(bytes.length).write(bytes);
    }
}
