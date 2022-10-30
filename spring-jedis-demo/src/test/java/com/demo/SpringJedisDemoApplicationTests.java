package com.demo;

import com.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringJedisDemoApplicationTests {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    void testString() {
        redisTemplate.opsForValue().set("name","老虎");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
    @Test
    void testSaveUser() {
        redisTemplate.opsForValue().set("user",new User("张三",18));
        Object name = redisTemplate.opsForValue().get("user");
        System.out.println(name);
    }
}
