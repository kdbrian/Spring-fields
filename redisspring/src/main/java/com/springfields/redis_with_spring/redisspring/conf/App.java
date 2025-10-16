package com.springfields.redis_with_spring.redisspring.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.UnifiedJedis;

@Component
public class App {

    @Value("${redis.host}")
    private String redis_host;

    @Value("${redis.port}")
    private String redis_port;


    @Bean
    public UnifiedJedis unifiedJedis() {
        try {
            return new UnifiedJedis(new HostAndPort(redis_host, Integer.parseInt(redis_port)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
