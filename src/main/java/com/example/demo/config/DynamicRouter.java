package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 动态网关的实现类
 * @author zhenwei.wang 2019/7/16
 */
@Service
public class DynamicRouter implements ApplicationEventPublisherAware {

    private static Logger log = LoggerFactory.getLogger(DynamicRouter.class);
    private ApplicationEventPublisher publisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


    @Qualifier("customRouteDefinitionRepository")
    @Autowired
    RouteDefinitionRepository definitionRepository;

    //获取所有路由列表
    public Flux<RouteDefinition> allRouters() {
        return definitionRepository.getRouteDefinitions();
    }

    public Mono<Void> add(RouteDefinition definition) {
        //TODO持久化。
        definitionRepository.save(Mono.just(definition)).subscribe();
        publisher.publishEvent(new RefreshRoutesEvent(this));
        return Mono.empty();
    }

    //更新路由
    public Mono<Void> update(RouteDefinition definition) {
        try {
            this.definitionRepository.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            log.warn("update route failed-1,", e);
        }
        try {
            definitionRepository.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
        } catch (Exception e) {
            log.warn("update route failed-2,", e);
        }
        return Mono.empty();
    }
    //删除路由
    public Mono<ResponseEntity<Object>> delete(String id) {
        return this.definitionRepository.delete(Mono.just(id))
                .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
                .onErrorResume(t -> t instanceof NotFoundException, t -> Mono.just(ResponseEntity.notFound().build()));
    }
}
