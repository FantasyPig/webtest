package com.alex.webtest.redis;

public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;

    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
        this.prefix = prefix;
        this.expireSeconds = 0;//0代表永远不过期
    }

    public int expireSeconds() {
        return this.expireSeconds;
    }

    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + this.prefix;
    }
}
