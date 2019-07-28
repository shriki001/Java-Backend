package com.ex4;

import com.ex4.listeners.MySessionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;

/**
 * class that config the Redis for session listener
 */

@Configuration
public class RedisConfig {
    private final redis.embedded.RedisServer redisServer;
    private final ApplicationContext context;


    public RedisConfig(@Value("${spring.redis.port}") final int redisPort, ApplicationContext context) throws IOException {
        this.redisServer = new redis.embedded.RedisServer(redisPort);
        this.context = context;
    }

    @PostConstruct
    public void startRedis() {
        this.redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        this.redisServer.stop();
    }

    @Bean
    public HttpSessionListener httpSessionListener() {
        return new MySessionListener(context);
    }
}
