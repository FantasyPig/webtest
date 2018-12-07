package com.alex.webtest.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String valueStr = jedis.get(realKey);
            T value = stringToBean(valueStr, clazz);
            return value;
        } finally {
            jedis.close();
        }
    }

    public <T> boolean set(KeyPrefix prefix, String key, T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String valueStr = beanToString(value);
            jedis.set(realKey, valueStr);
        } finally {
            jedis.close();
        }
        return false;
    }

    private <T> String beanToString(T value){
        if(value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class
                || clazz == String.class || clazz == long.class
                ||clazz == Long.class){
            return value + "";
        } else{
            return JSON.toJSONString(value);
        }
    }

    private <T> T stringToBean(String str, Class<T> clazz){
        if(str == null || str.length() == 0 || clazz == null){
            return null;
        }
        if(clazz == int.class || clazz == Integer.class){
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class){
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class){
            return (T) Long.valueOf(str);
        } else{
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }



}
