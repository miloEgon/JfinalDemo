package com.demo.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Redis;

public class RedisController extends Controller {

    /*public static void main(String[] args) {
        // 获取名称为bbs的Redis Cache对象
        Cache bbsCache = Redis.use("bbs");
        bbsCache.set("dean", "hero");
        System.out.println(bbsCache.get("dean"));
    }*/

    @Clear
    public void index() {
        /*// 获取名称为bbs的Redis Cache对象
        Redis.use().set("key", "milo");
        renderText((String) Redis.use().get("key"));*/
        String set = Redis.use("bbs").set("key", "milo");
        renderText(set);
    }
}
