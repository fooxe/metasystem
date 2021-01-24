package com.dashow.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * MetaSystemPro
 *
 * @author: Create MainConfig  by Fuqifeng on 2021 2021/1/23;
 * Function:
 */
@Configuration
public class MainConfig {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public SqlLogInterceptor sqlLogInterceptor() {
//        return new SqlLogInterceptor();
//    }
//
//
//    @Bean
//    @ConditionalOnMissingBean(HandlerInterceptor.class)
//    @ConditionalOnProperty(prefix = "paascloud.token.interceptor", name = "enable", havingValue = "true")
//    public TokenInterceptor tokenInterceptor() {
//        return new TokenInterceptor();
//    }
}
