package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Hedon Wang
 * @create 2020-09-27 09:15
 */

@SpringBootApplication
@EnableEurekaClient
public class ConfigClient3366 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClient3366.class,args);
    }
}
