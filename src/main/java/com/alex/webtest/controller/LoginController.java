package com.alex.webtest.controller;

import com.alex.webtest.redis.RedisService;
import com.alex.webtest.result.Result;
import com.alex.webtest.service.UserService;
import com.alex.webtest.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("")
    public String toLog(){
        return "login";
    }

    @RequestMapping("do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response,
        @Valid LoginVo loginVo){
        String token = userService.login(response, loginVo);
        return Result.success(token);
    }















}
