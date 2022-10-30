package com.demo;

import com.demo.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class StringRedisDemoApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;
    // JSON序列化工具
    private static final ObjectMapper mapper = new ObjectMapper();
    @Test
    void testString() {
        redisTemplate.opsForValue().set("name","老虎");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
    @Test
    void testSaveUser() {
        User user = new User("张三", 18);
        String json = null;
        try {
            json = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        redisTemplate.opsForValue().set("user",json);
        String user1 = redisTemplate.opsForValue().get("user");
        User user2 = null;
        try {
            user2 = mapper.readValue(user1, User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(user2);
    }
    @Test
    void testHash() {
        redisTemplate.opsForHash().put("user:400","name","小张");
        redisTemplate.opsForHash().put("user:400","age","25");
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("user:400");
        System.out.println(entries);
    }
}
