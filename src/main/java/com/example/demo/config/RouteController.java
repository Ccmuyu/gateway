package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.handler.predicate.*;

/**
 * @author zhenwei.wang 2019/7/16
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;
    //增加路由
    @PostMapping("/add")
    public String add(@RequestBody PredicateDefinition gwdefinition) {
        try {
            RouteDefinition definition = assembleRouteDefinition(gwdefinition);
            return this.dynamicRouteService.add(definition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "succss";
    }
    //删除路由
    @DeleteMapping("/routes/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        return this.dynamicRouteService.delete(id);
    }
    //更新路由
    @PostMapping("/update")
    public String update(@RequestBody RouteDefinition definition) {
        return this.dynamicRouteService.update(definition);
    }

    private RouteDefinition assembleRouteDefinition(PredicateDefinition definition) {
        RouteDefinition d = new RouteDefinition();
        d.getPredicates().add(definition);
        return d;
    }

}
