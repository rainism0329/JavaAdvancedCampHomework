package com.example.redisdemo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @version 1.0
 * @program: redis-demo
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/10/25 1:32 AM
 */
@Component
public class RedisCounter {
    private Long count = 0L;

    private final static String KEY = "inventory-counter";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setCount(Long count) {
        this.count = count;
        redisTemplate.delete(KEY);
    }

    public boolean reduce() {

        Long leftCount = redisTemplate.opsForValue().increment(KEY, 1);

        if (Objects.isNull(leftCount)) {
            return false;
        }

        return leftCount <= count;
    }
}
