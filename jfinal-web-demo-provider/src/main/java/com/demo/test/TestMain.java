package com.demo.test;

import redis.clients.jedis.Jedis;

public class TestMain {

    public static final Jedis jedis = new Jedis("122.112.216.37");

    public static void main(String[] args) {
        jedis.auth("Xjq/dj2019");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        String set = jedis.set("name", "milo");
        System.out.println(set);
        System.out.println(jedis.get("name"));
    }
}
