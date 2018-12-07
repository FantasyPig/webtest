package com.alex.webtest.controller;

import com.alex.webtest.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    RedisService redisService;

    @RequestMapping("/hello")
    @ResponseBody
    public String testHello(){
        return "Hello World!";
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public String testRedisSet(){

        return null;
    }

}
