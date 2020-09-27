package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Hedon Wang
 * @create 2020-09-27 11:05
 */
//@Service //这里不需要写@Service，因为这里不是常规的 dao、service、controller
@EnableBinding(Source.class) //定义消息的推送通道，这里的Source包不要导错
public class MessageProviderImpl implements IMessageProvider {

    //注入消息发送管道 org.springframework.cloud.stream.messaging.DirectWithAttributesChannel
    @Resource
    private MessageChannel output;

    //发送消息
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("***********serial :"+serial);
        System.out.println(output.getClass());
        return serial;
    }
}
