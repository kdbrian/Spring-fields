package com.springfields.redis_with_spring.redisspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.UnifiedJedis;

@SpringBootApplication
public class RedisspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisspringApplication.class, args);
	}

	@Bean
	public UnifiedJedis unifiedJedis(){
		return new UnifiedJedis("redis://localhost:6379");
	}
}
