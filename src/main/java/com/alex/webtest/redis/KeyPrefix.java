package com.alex.webtest.redis;

public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();

}
