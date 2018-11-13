package com.wkyc.member.core.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
public class RedisConfig {

    @Bean("clusterRedis")
    public RedissonClient clusterRedisBean() throws IOException {
        Resource resource = new ClassPathResource("redis/clusterRedis.json");
        Config config = Config.fromJSON(new InputStreamReader(resource.getInputStream()));
        return Redisson.create(config);
    }


}