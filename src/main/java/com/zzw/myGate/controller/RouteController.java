package com.zzw.myGate.controller;

import com.zzw.myGate.config.DynamicRouter;
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

    private final DynamicRouter dynamicRouteService;

    public RouteController(DynamicRouter dynamicRouteService) {
        this.dynamicRouteService = dynamicRouteService;
    }

    /**
     * all routes
     *
     * @return
     */
    @GetMapping("/routes")
    public Flux<RouteDefinition> routes() {
        return dynamicRouteService.allRouters();
    }

    /**
     * add one routes
     *
     * @param definition route definition
     * @return
     */
    //增加路由
    @PostMapping("/add")
    public String add(@RequestBody RouteDefinition definition) {
        dynamicRouteService.add(definition);
        return "success";
    }

    /**
     * delete one route
     *
     * @param id
     * @return
     */
    //删除路由
    @DeleteMapping("/routes/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        return this.dynamicRouteService.delete(id);
    }

    /**
     * update one route
     *
     * @param definition
     * @return
     */
    //更新路由
    @PostMapping("/update")
    public String update(@RequestBody RouteDefinition definition) {
        dynamicRouteService.update(definition);
        return "success";
    }

}
