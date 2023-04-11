package hm1.data;

import hm1.data.Quality_Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class RedisConfiguration { 

    @Bean
    public RedisTemplate<String, Quality_Info> getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Quality_Info> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }
}