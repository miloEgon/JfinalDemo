package com.demo.config;

import com.demo.common.Dict;
import com.demo.common.MappingModel;
import com.demo.common.Play;
import com.demo.common.ZbusRpcPlugin;
import com.jfinal.config.Plugins;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.mysql.jdbc.Connection;
import redis.clients.jedis.JedisPoolConfig;

public class PluginFactory {


    public static void startActiveRecordPlugin(Plugins me) {
        DruidPlugin dp = new DruidPlugin(
                getProperty(Dict.CONFIG_JDBC_URL),
                getProperty(Dict.CONFIG_JDBC_USERNAME),
                getProperty(Dict.CONFIG_JDBC_PASSWORD).trim()
        );

        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        if ( getPropertyToBoolean(Dict.CONFIG_JFINAL_MODE, false) ) {
            arp.setShowSql(true);
        }
        arp.setTransactionLevel(Connection.TRANSACTION_READ_COMMITTED);

        MappingModel.mapping(arp);

        me.add(dp);
        me.add(arp);
    }

    public static void startRedisPlugin(Plugins me, String cacheName) {
        RedisPlugin redis = new RedisPlugin(
                cacheName,
                getProperty(Dict.CONFIG_REDIS_IP),
                getPropertyToInt(Dict.CONFIG_REDIS_PORT, 9379),
                getProperty(Dict.CONFIG_REDIS_PASSWORD)
        );

        JedisPoolConfig jpc = redis.getJedisPoolConfig();
        jpc.setMaxTotal(getPropertyToInt(Dict.CONFIG_REDIS_MAXTOTAL, 30));
        jpc.setMaxIdle(getPropertyToInt(Dict.CONFIG_REDIS_MAXIDLE, 10));
        jpc.setMaxWaitMillis(getPropertyToInt(Dict.CONFIG_REDIS_MAXWAIT_MILLIS, 3000));
        jpc.setMinEvictableIdleTimeMillis(getPropertyToInt(Dict.CONFIG_REDIS_MINEVIC_MILLIS, 600000));
        jpc.setSoftMinEvictableIdleTimeMillis(getPropertyToInt(Dict.CONFIG_REDIS_SOFTMINEVIC_MILLIS, 600000));
        jpc.setTimeBetweenEvictionRunsMillis(getPropertyToInt(Dict.CONFIG_REDIS_TIMEBETWEENEVICRUN_MILLIS, 300000));

        me.add(redis);
    }

    public static void startZbusPlugin(Plugins me) {
        ZbusRpcPlugin zbusRpcPlugin = new ZbusRpcPlugin(Play.getProperty("rpc.zbus.host"));
        me.add(zbusRpcPlugin);
    }

    public static String getProperty(String key) {
        return PropKit.use("play.properties", "UTF-8").get(key);
    }

    public static Boolean getPropertyToBoolean(String key, Boolean defaultValue) {
        return PropKit.use("play.properties", "UTF-8").getBoolean(key, defaultValue);
    }

    public static Integer getPropertyToInt(String key, Integer defaultValue) {
        return PropKit.use("play.properties", "UTF-8").getInt(key, defaultValue);
    }


}
