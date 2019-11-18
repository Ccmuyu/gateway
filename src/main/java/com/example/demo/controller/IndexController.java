package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author zhenwei.wang 2019/7/23
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/")
    public Mono<Object> index() {
        return Mono.just("hello!");
    }
}
