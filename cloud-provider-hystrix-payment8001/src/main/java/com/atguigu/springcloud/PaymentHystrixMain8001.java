package com.atguigu.springcloud;

import com.atguigu.springcloud.entities.Payment;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

/**
 * @author Hedon Wang
 * @create 2020-05-08 20:43
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker   //启动断路器
@EnableHystrix
public class PaymentHystrixMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class,args);
    }

    /**
     * 此配置是为了服务监控而配置，与服务容错本身无关。
     * 这个SpringCloud升级后的坑。
     * ServletRegistrationBean因为SpringBoot默认路径不是"/hystrix.stream"。
     * 只要在自己的项目里配置上下模的servlet就可以了。
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
