package com.demo.config;

import com.demo.entity.User;
import com.demo.interceptor.GlobalActionInterceptor;
import com.demo.interceptor.GlobalServiceInterceptor;
import com.demo.routers.AdminRoutes;
import com.demo.routers.FrontRoutes;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import redis.clients.jedis.JedisPoolConfig;

public class DemoConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setUrlParaSeparator("&");
        constants.setViewType(ViewType.JFINAL_TEMPLATE);
        /*constants.setBaseDownloadPath("files");*/
        constants.setBaseDownloadPath("D:/downloads");
        constants.setBaseUploadPath("files");
        // 开启对 jfinal web 项目组件 Controller、Interceptor、Validator 的注入
        constants.setInjectDependency(true);
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add(new FrontRoutes());
        routes.add(new AdminRoutes());
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin dp = new DruidPlugin("jdbc:mysql://122.112.216.37/demo", "root", "Xjq/dj2019");
        plugins.add(dp);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        plugins.add(arp);
        arp.addMapping("user", User.class);

//        RedisPlugin redis = new RedisPlugin("bbs","122.112.216.37", 6379,"Xjq/dj2019");
//        JedisPoolConfig jpc = redis.getJedisPoolConfig();
//        jpc.setMaxTotal(30);
//        jpc.setMaxIdle(10);
//        jpc.setMaxWaitMillis(3000);
//        jpc.setMinEvictableIdleTimeMillis(600000);
//        jpc.setSoftMinEvictableIdleTimeMillis(600000);
//        jpc.setTimeBetweenEvictionRunsMillis(300000);
//        plugins.add(redis);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        // 添加控制层全局拦截器
        me.addGlobalActionInterceptor(new GlobalActionInterceptor());
        // 添加业务层全局拦截器
        me.addGlobalServiceInterceptor(new GlobalServiceInterceptor());
        /*// 为兼容老版本保留的方法，功能与addGlobalActionInterceptor完全一样
        me.add(new GlobalActionInterceptor());*/
    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
