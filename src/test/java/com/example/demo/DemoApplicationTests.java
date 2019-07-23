package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Qualifier("redisTemplate")
    @Autowired
    RedisTemplate redisTemplate;
    private static Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);
    @Test
    public void contextLoads() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("1a1", "aaa");
        map.put("1a2", "bbb");
        map.put("1a1", "ccc");

        String testMap = "testMap";
        hashOperations.putAll(testMap,map);
        Object o = hashOperations.get(testMap, "1a1");
        log.info("o:{}", o);
        List list = hashOperations.multiGet(testMap, Arrays.asList("1a1", "1a2"));
    }

}
