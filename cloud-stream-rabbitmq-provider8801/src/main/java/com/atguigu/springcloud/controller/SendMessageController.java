package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Hedon Wang
 * @create 2020-09-27 11:11
 */

@RestController
public class SendMessageController {

    @Resource

    private IMessageProvider messageProvider;


    @GetMapping("/sendMessage")
    public String sendMessage(){
        return messageProvider.send();
    }

}
