package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Hedon Wang
 * @create 2020-05-02 10:04
 */

@RestController
@Slf4j
public class OrderFeignController {

    //注入服务调用接口
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumerFeign/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);   //调用服务
    }


    @GetMapping(value ="/consumerFeign/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return paymentFeignService.create(payment);     //调用服务
    }

    @GetMapping(value = "/consumerFeign/payment/timeout")
    public String paymentFeignTimeout(){
        //openfeign-client 客户端一般默认等待 1 秒钟，但是我们这里故意让它暂停了 3 秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
