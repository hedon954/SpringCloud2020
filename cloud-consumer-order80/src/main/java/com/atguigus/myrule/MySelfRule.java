package com.atguigus.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hedon Wang
 * @create 2020-05-02 08:53
 */
@Configuration
public class MySelfRule {


    @Bean
    public IRule myRule(){
        return new RandomRule();   //定义为随机
    }
}
