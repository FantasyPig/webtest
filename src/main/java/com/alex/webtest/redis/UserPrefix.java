package com.alex.webtest.redis;

public class UserPrefix extends BasePrefix {

    public static UserPrefix getById(){
        return new UserPrefix(30, "id");
    }

    public static UserPrefix getByName(){
        return new UserPrefix(30, "name");
    }

    private UserPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    private UserPrefix(String prefix) {
        super(prefix);
    }
}
