package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Hedon Wang
 * @create 2020-05-08 20:45
 */
@Service
public class PaymentService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK, id:"+id+"\t"+"哈哈哈~~";
    }


    /**
     * 超时
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            //规定这个线程超时的时间为3秒钟
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        /**
         * 约定：
         *      < 3s ：正常
         *      > 3s ：超时
         */
        int timeNumber = 5;  //超过峰值了
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
//        int age = 10/0;   //如果是报错了?
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeOut, id:"+id+"\t"+"哈哈哈~~" + "超时"+timeNumber+"秒钟";
    }


    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"  系统繁忙或者运行报错，请稍后再试, id:"+id+"\t"+"┭┮﹏┭┮~~";
    }

    //========》服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),                         //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),            //请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),      //失败后要重试需要睡眠多久
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")           //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        if(id<0){
            throw new RuntimeException("****** id 不能为负数");
        }
        //String serialNumber = UUID.randomUUID().toString();
        //流水号
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id")Integer id){
        return "id 不能为负数，请稍后调试，┭┮﹏┭┮   id："+id;
    }

}
