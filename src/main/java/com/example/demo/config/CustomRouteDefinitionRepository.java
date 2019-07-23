package com.example.demo.config;

import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author zhenwei.wang 2019/7/17
 */
@Service
public class CustomRouteDefinitionRepository extends InMemoryRouteDefinitionRepository {

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        Mono<Void> save = super.save(route);
        //TODO 持久化db，redis都可以
        return save;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        Mono<Void> delete = super.delete(routeId);
        //TODO
        return delete;
    }
}
