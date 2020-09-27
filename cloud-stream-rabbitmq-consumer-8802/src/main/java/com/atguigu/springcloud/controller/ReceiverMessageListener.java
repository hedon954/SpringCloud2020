package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hedon Wang
 * @create 2020-09-27 19:56
 */
@RestController
@EnableBinding(Sink.class) //绑定接收消息通道，消费者是 Sink，生产者是 Source
public class ReceiverMessageListener {

    @Value("${server.port}")
    private String serverPort;

    //监听
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者1号，-------->接收到的消息："+message.getPayload()+"\t port: "+serverPort);
    }

}
