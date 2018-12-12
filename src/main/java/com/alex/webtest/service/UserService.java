package com.alex.webtest.service;

import com.alex.webtest.dao.UserDao;
import com.alex.webtest.exception.GlobalException;
import com.alex.webtest.redis.RedisService;
import com.alex.webtest.redis.UserPrefix;
import com.alex.webtest.result.CodeMsg;
import com.alex.webtest.utils.MD5Util;
import com.alex.webtest.utils.UUIDUtil;
import com.alex.webtest.utils.User;
import com.alex.webtest.vo.LoginVo;
import com.sun.tools.javac.jvm.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    UserDao dao;

    @Autowired
    RedisService redisService;

    public User getById(Long id){
        User user = redisService.get(UserPrefix.getById(), "" + id, User.class);
        if(user == null){
            user = dao.getById(id);
            if(user != null){
                redisService.set(UserPrefix.getById(), "" + id, user);
            }
        }
        return user;
    }

    public String login(HttpServletResponse response, LoginVo loginVo){
        if(loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formpass = loginVo.getPassword();
        User user = getById(Long.parseLong(mobile));
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbpass = user.getPassword();
        String saltDb = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formpass, saltDb);
        if(!calcPass.equals(dbpass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return token;
    }

    private void addCookie(HttpServletResponse response, String token, User user){
        redisService.set(UserPrefix.token(), token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(UserPrefix.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
