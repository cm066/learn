package com.cm.gatewaydemo.config;


import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;


/**
 * @author Administrator
 */
@Configuration
public class WebFluxConfiguration {


    public static final String CACHE_BODY_KEY = "cache_body_key";
    public static final String CONTENT_TYPE = "Content-Type";

    public static final String CONTENT_TYPE_JSON = "application/json";
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter decoratorBodyFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request     = exchange.getRequest();
            String            contentType = request.getHeaders().getFirst(CONTENT_TYPE);
            String            method      = request.getMethodValue();
//            String            contentType = request.getHeaders().get(CONTENT_TYPE).get(0);
            HashMap<String, Object> mapJson = new HashMap<>();


            final String path = ParamBodyUtil.getPath(exchange);
            // GatewayContext中目前只有一个String的path和String的requestBody，如果需要别的参数在GatewayContext中追加即可，因为这只是一个实体类，所以就不放源码了，各位需要什么就往里面放什么即可
            final GatewayContext gatewayContext = new GatewayContext();
            gatewayContext.setPath(path);

            if (null != contentType && contentType.contains(CONTENT_TYPE_JSON)) {

                DataBuffer defaultBuffer = exchange.getResponse().bufferFactory().allocateBuffer(0);
                System.out.println(Thread.currentThread().getId()+":main");
                return DataBufferUtils.join(exchange.getRequest().getBody())
                        .defaultIfEmpty(defaultBuffer)
                        .flatMap(parentBuffer -> {
                            byte[] bytes = new byte[parentBuffer.readableByteCount()];
                            System.out.println(new String(bytes));
                            System.out.println(Thread.currentThread().getId()+":aysn");
                            parentBuffer.read(bytes);
                            DataBufferUtils.release(parentBuffer);
                            DataBufferUtils.release(defaultBuffer);
                            Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                                DataBuffer buffer = ParamBodyUtil.getDataBuffer(exchange.getResponse().bufferFactory(), bytes);

                                return Mono.just(buffer);
                            });
//                                    DataBuffer derivedBuffer = parentBuffer.slice(0, parentBuffer.readableByteCount());
//                                    DataBufferUtils.retain(derivedBuffer);
//                                    Flux<DataBuffer> cachedFlux = Flux
//                                            .defer(() -> Flux.just(derivedBuffer));
                            ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                                @Override
                                public Flux<DataBuffer> getBody() {
                                    return cachedFlux;
                                }
                            };
                            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
                            return ServerRequest.create(mutatedExchange, HandlerStrategies.withDefaults().messageReaders())
                                    .bodyToMono(String.class)
                                    .doOnNext(objectValue -> {
                                        //在此处,将body中的params值获取到,并存放在本次请求的attributes属性中,这样就可以在本次请求中的所有地方进行使用了
                                        mutatedExchange.getAttributes().put(CACHE_BODY_KEY, objectValue);
                                    })

                                    .then(chain.filter(mutatedExchange));

                        });
            }
            return chain.filter(exchange);
        };
    }
}
