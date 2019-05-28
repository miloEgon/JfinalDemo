package com.demo.config;

import com.demo.common.Dict;
import com.demo.entity.User;
import com.demo.interceptor.GlobalActionInterceptor;
import com.demo.interceptor.GlobalServiceInterceptor;
import com.demo.routers.AdminRoutes;
import com.demo.routers.FrontRoutes;
import com.demo.utils.Secrets;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.mysql.jdbc.Connection;
import redis.clients.jedis.JedisPoolConfig;

public class DemoConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants constants) {
        constants.setUrlParaSeparator("&");
        constants.setViewType(ViewType.JFINAL_TEMPLATE);
        /*constants.setBaseDownloadPath("files");*/
        constants.setBaseDownloadPath("D:/downloads");
        constants.setBaseUploadPath("files");
        constants.setInjectDependency(true); // 开启对 jfinal web 项目组件 Controller、Interceptor、Validator 的注入

        loadPropertyFile("play.properties");
        constants.setDevMode(getPropertyToBoolean(Dict.CONFIG_JFINAL_MODE, false));
      //constants.setDevMode(true);
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
        //配置Mysql数据库连接相关配置
        PluginFactory.startActiveRecordPlugin(plugins);
        //配置Redis缓存相关配置
        PluginFactory.startRedisPlugin(plugins, "bbs");


        /*DruidPlugin dp = new DruidPlugin(Secrets.jdbcUrl, Secrets.jdbcUsername, Secrets.jdbcPassword);
        plugins.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.addMapping("user", "id", User.class);
        arp.setShowSql(true);
        arp.setTransactionLevel(Connection.TRANSACTION_READ_COMMITTED);
        plugins.add(arp);*/
        /*RedisPlugin redis = new RedisPlugin("bbs", Secrets.redisHost, Secrets.redisPort, Secrets.redisPwd);
        JedisPoolConfig jpc = redis.getJedisPoolConfig();
        jpc.setMaxTotal(30);//最大活动对象数
        jpc.setMaxIdle(10);//最大能够保持idel状态的对象数
        jpc.setMaxWaitMillis(30000);//当池内没有返回对象时，最大等待时间
        jpc.setTestWhileIdle(true);//如果为true，表示有一个idle object evitor线程对idle object进行扫描，如果validate失败，此object会被从pool中drop掉；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义；
        jpc.setMinEvictableIdleTimeMillis(600000);//表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义；
        jpc.setSoftMinEvictableIdleTimeMillis(600000);//在minEvictableIdleTimeMillis基础上，加入了至少minIdle个对象已经在pool里面了。如果为-1，evicted不会根据idle time驱逐任何对象。如果minEvictableIdleTimeMillis>0，则此项设置无意义，且只有在timeBetweenEvictionRunsMillis大于0时才有意义；
        jpc.setTimeBetweenEvictionRunsMillis(300000);//“空闲链接”检测线程，检测的周期，毫秒数。如果为负值，表示不运行“检测线程”。默认为-1
        plugins.add(redis);*/
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
