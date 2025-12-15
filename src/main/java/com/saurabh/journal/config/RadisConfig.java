package com.saurabh.journal.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RadisConfig {
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//
//        RedisStandaloneConfiguration config =
//                new RedisStandaloneConfiguration();
//        config.setHostName("redis-12194.crce217.ap-south-1-1.ec2.cloud.redislabs.com");
//        config.setPort(12194);
//        config.setUsername("default");
//        config.setPassword("hnjwHb2waR2nEotyznjaKazr9SmbAUmO");
//
//        LettuceClientConfiguration clientConfig =
//                LettuceClientConfiguration.builder()
//                        .useSsl()
//                        .build();
//
//        return new LettuceConnectionFactory(config, clientConfig);
//    }
    @Bean
    public RedisTemplate<String, String>  redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
