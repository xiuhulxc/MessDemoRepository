package com.example.demo1.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/5/19 16:52
 * @version: 1.0
 */
@Slf4j
@Configurable
public class RedissonConfig {

    private final String REDISSON_PREFIX = "redis://";
    private final RedisProperties redisProperties;


    public RedissonConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        String[] urls = {"localhost:6379", "localhost:6380"};
        config.useClusterServers()
                .addNodeAddress(urls);
        RedissonClient redissonClient = Redisson.create(config);
        return  redissonClient;
    }

}
