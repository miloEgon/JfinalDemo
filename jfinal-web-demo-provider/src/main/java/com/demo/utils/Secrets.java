package com.demo.utils;

import redis.clients.jedis.Jedis;

public class Secrets {

    //小程序ID
    public static final String appId = "wxb293e104076401fc";

    //小程序密钥
    public static final String appSecret = "47099fa82e776cc3991f9b8ce0206fe5";

    //redis主机
    public static final String redisHost = "122.112.216.37";

    //redis密码
    public static final String redisPwd = "Xjq/dj2019";

    //redis端口
    public static final Integer redisPort = 6379;

    //mysql主机
    public static final String jdbcUrl = "jdbc:mysql://122.112.216.37/demo";

    //mysql用户
    public static final String jdbcUsername = "root";

    //mysql密码
    public static final String jdbcPassword = "Xjq/dj2019";

    public static final Jedis jedis = new Jedis(Secrets.redisHost);

    static {
        jedis.auth(Secrets.redisPwd);
    }

}
