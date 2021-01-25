package com.dashow.metasystem.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * MetaSystemPro
 *
 * @author: Create MetaSysMainApplication  by Fuqifeng on 2021 2021/1/23;
 * Function:
 */

@EnableCircuitBreaker
//@EnableHystrixDashboard
@EnableSwagger2
//@EnableCMDBConfig
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
@EnableCaching
//@EnableHystrix
@EnableFeignClients
@EnableConfigServer
@EnableDiscoveryClient
@EnableTransactionManagement
public class MetaSysMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetaSysMainApplication.class, args);
    }

}
