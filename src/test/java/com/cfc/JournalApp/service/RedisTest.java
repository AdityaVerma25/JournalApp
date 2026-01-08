package com.cfc.JournalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("default")
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    public void testRedisService() {
        redisTemplate.opsForValue().set("email", "adityaverma@gmail.com");

        System.out.println(
                redisTemplate.getConnectionFactory().getConnection().ping()
        );
    }

}
