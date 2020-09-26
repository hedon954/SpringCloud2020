package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Hedon Wang
 * @create 2020-05-08 21:58
 */

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand  //没有自己指明fallback，那么就用全局的
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id){
//        int age = 10/0;  //如果是自己出错
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    //自己的兜底方法
    public String paymentTimeOutFallbackMethod(Integer id){
        return "我是消费者80，对方支付系统繁忙请10秒钟后再试试或者自己运行出错请检查自己┭┮﹏┭┮";
    }


    //全局Fallback,注意！全局的Fallback不能带参数
    public String payment_Global_FallbackMethod(){
        return "Global 异常处理信息，请稍后再试 ┭┮﹏┭┮";
    }
}
