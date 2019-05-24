package com.demo.test;

import com.demo.utils.Secrets;
import com.jfinal.plugin.redis.Redis;

public class TestMain {

    public static void main(String[] args) {
//        String openId = "ZDJFGADNFAK012FDSFF121";
//        String sessionId = "ZGJKEJADJKADFDKDKFAKFL";
//
//        //查看服务是否运行
//        System.out.println("服务正在运行: "+ Secrets.jedis.ping());
////        String set = jedis.set("name", "milo");
////        System.out.println(set);
//        System.out.println(Secrets.jedis.get("name"));
//        Secrets.jedis.set(openId,sessionId);
//        System.out.println(Secrets.jedis.get(openId));

//        Object o = Redis.use("bbs").get("name");
        String s = Secrets.jedis.get("name");
        System.out.println(s);

    }
}
