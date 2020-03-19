package com.zzw.myGate.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhenwei.wang 2019/7/16
 */
@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/hello/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8090/hello/web"))
                .route(r -> r.path("/baidu").uri("http://www.baidu.com"))
                .build();
    }

}
