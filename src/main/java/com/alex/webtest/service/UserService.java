package com.alex.webtest.service;

import com.alex.webtest.dao.UserDao;
import com.alex.webtest.utils.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao dao;

    public User getById(){
        User user1 = dao.getById(12345678900L);
        return user1;
    }

//    public int addUser(){
//        User user2 = new User();
//        user2.setId(13377775453L);
//        user2.setName("user2");
//        return dao.insert(user2);
//    }
}
