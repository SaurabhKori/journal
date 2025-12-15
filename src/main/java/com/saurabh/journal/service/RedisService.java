package com.saurabh.journal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    public <T> T getValue(String key, Class<T> clazz){
        try {
            Object value = redisTemplate.opsForValue().get(key);
            if(value==null)
                return null;
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(value.toString(),clazz);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
            return null;
        }

    }
    public void setValue(String key, Object value,Long expireTime){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(value);
           redisTemplate.opsForValue().set(key,json,expireTime, TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error(e.getMessage(),e);

        }

    }

}
