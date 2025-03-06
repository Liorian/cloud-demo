package org.example.order.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderServiceConfig {

    /**
     * 开启重试机制
     *
     * @return Retryer
     */
    @Bean
    Retryer retryer() {
        return new Retryer.Default();
    }


    /**
     * 开启Feign日志
     *
     * @return Logger.Level
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @LoadBalanced // 注解式负载均衡
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
