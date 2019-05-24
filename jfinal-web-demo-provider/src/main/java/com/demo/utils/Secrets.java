package com.demo.utils;

import redis.clients.jedis.Jedis;

public class Secrets {

    //小程序ID
    public static final String appId = "wxb293e104076401fc";

    //小程序密钥
    public static final String appSecret = "47099fa82e776cc3991f9b8ce0206fe5";

    public static final String redisHost = "122.112.216.37";

    public static final String redisPwd = "Xjq/dj2019";

    public static final Integer redisPort = 6379;

    public static final String jdbcUrl = "jdbc:mysql://122.112.216.37/demo";

    public static final String jdbcUsername = "root";

    public static final String jdbcPassword = "Xjq/dj2019";

    public static final Jedis jedis = new Jedis(Secrets.redisHost);

    static {
        jedis.auth(Secrets.redisPwd);
    }

}
