package com.example.demo.controller;

import com.example.demo.config.DynamicRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zhenwei.wang 2019/7/16
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private DynamicRouter dynamicRouteService;

    @GetMapping("/routes")
    public Flux<RouteDefinition> routes() {
        return dynamicRouteService.allRouters();
    }

    //增加路由
    @PostMapping("/add")
    public String add(@RequestBody RouteDefinition definition) {
        dynamicRouteService.add(definition);
        return "success";
    }
    //删除路由
    @DeleteMapping("/routes/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        return this.dynamicRouteService.delete(id);
    }
    //更新路由
    @PostMapping("/update")
    public String update(@RequestBody RouteDefinition definition) {
        dynamicRouteService.update(definition);
        return "success";
    }

}
