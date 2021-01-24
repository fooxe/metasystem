package com.dashow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * MetaSystemPro
 *
 * @author: Create MetaSysMainApplication  by Fuqifeng on 2021 2021/1/23;
 * Function:
 */

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class MetaSysMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetaSysMainApplication.class, args);
    }

}
