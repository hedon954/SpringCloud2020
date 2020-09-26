package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hedon Wang
 * @create 2020-04-22 21:33
 */

@RestController
//打日志
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * Controller是传给前端的，我们传CommonResult而不传Payment
     */

    @PostMapping(value = "/payment/create")
    //consumer是客户端，提交有关payment过来，我们服务端要搞一个RequestBody才能接收
//  public CommonResult create(@RequestBody  Payment payment){
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果："+result);

        if(result > 0){
            return new CommonResult(200,"插入数据成功, serverPort:"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("**********查询结果："+payment+"\t"+"哈哈哈哈");

        if (payment != null){
            return new CommonResult(200,"查询成功,serverPort: "+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录，查询ID:"+id,null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for(String element:services){
            log.info(element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance:instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }


        return this.discoveryClient;

    }

    @GetMapping("/payment/lb")
    public String getPaymentByLB(){
        return serverPort;
    }
}
