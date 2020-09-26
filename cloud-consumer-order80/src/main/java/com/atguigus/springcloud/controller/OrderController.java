package com.atguigus.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Hedon Wang
 * @create 2020-04-24 20:03
 */

@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";


    @Resource//Resource是按照名称来注入，Autowired是按照类型来注入（可以搭配Qualifier实现按照名注入）
    private RestTemplate restTemplate;
    /**
     * 读 ：客户端都是发GetMapping这个请求
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return  restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    /**
     * Entity获得的内容不只是Json对象，还有一些请求头，获取Json对象需要.GetBody()
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        //Entity多了一些头
        if(entity.getStatusCode().is2xxSuccessful()){
            log.info(entity.getStatusCode()+"\t"+entity.getStatusCodeValue());
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }



    /**
     * 写
     * @param payment
     * @return
     */
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        /**
         * url：REST 请求地址
         * requestMap：请求参数
         * ResponseBean.class：HTTP响应转换成被转换成的对象类型
         */
        //读GET，写POST
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/createByPostForEntity")
    public CommonResult<Payment> create2(Payment payment){
        return restTemplate.postForEntity(PAYMENT_URL+"/payment/create",payment,CommonResult.class).getBody();
    }
}
