package com.demo.config;

import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.template.Engine;

public class AppConfig extends JFinalConfig {

    public void configConstant(Constants me) {
        // 第一次使用use加载的配置将成为主配置，可以通过PropKit.get(...)直接取值
        PropKit.use("a_little_config.txt");
        me.setDevMode(PropKit.getBoolean("devMode"));
    }

    public void configRoute(Routes me) {

    }

    public void configEngine(Engine me) {

    }

    public void configPlugin(Plugins me) {
        // 非第一次使用use加载的配置，需要通过每次使用use来指定配置文件名再来取值
        String redisHost = PropKit.use("redis_config.txt").get("host");
        int redisPort = PropKit.use("redis_config.txt").getInt("port");
        RedisPlugin rp = new RedisPlugin("myRedis", redisHost, redisPort);
        me.add(rp);

        // 非第一次使用 use加载的配置，也可以先得到一个Prop对象，再通过该对象来获取值
        Prop p = PropKit.use("db_config.txt");
        DruidPlugin dp = new DruidPlugin(p.get("jdbcUrl"), p.get("username"), p.get("password"));
        me.add(dp);
    }

    public void configInterceptor(Interceptors me) {

    }

    public void configHandler(Handlers me) {

    }

    public static void main(String[] args) {
        PropKit.use("a_little_config.txt");
        System.out.println(PropKit.getBoolean("devMode"));
        PropKit.useless("a_little_config.txt");
        System.out.println(PropKit.getBoolean("devMode"));
    }
}
