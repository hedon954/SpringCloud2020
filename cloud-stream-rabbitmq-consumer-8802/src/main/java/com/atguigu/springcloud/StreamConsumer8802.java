package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Hedon Wang
 * @create 2020-09-27 19:54
 */

@SpringBootApplication
@EnableEurekaClient
public class StreamConsumer8802 {

    public static void main(String[] args) {
        SpringApplication.run(StreamConsumer8802.class,args);
    }

}
