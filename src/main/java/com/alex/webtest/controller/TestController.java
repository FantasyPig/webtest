package com.alex.webtest.controller;

import com.alex.webtest.redis.RedisService;
import com.alex.webtest.redis.UserPrefix;
import com.alex.webtest.result.Result;
import com.alex.webtest.service.UserService;
import com.alex.webtest.utils.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    RedisService redisService;

    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public String testHello(){
        return "Hello World!";
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<String> testRedisSet(){
        String s = "okokok";
        Boolean flag = redisService.set(UserPrefix.getById(), "1", s);
        return Result.success(flag);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<List<String>> testRedisGet(){
        String s = redisService.get(UserPrefix.getById(), "1", String.class);
        return Result.success(s);
    }

    @RequestMapping("/mybatis/get")
    @ResponseBody
    public Result<User> testMybatisGet(){
        User user = userService.getById();
        return Result.success(user);
    }

    @RequestMapping("/login")
    public String testLogin(){
        return "login";
    }

//    @RequestMapping("/mybatis/add")
//    @ResponseBody
//    public Result<Boolean> testMybatisAdd(){
//        return Result.success(userService.addUser());
//    }
}
