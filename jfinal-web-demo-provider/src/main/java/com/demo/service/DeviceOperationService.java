package com.demo.service;

import com.jfinal.plugin.redis.Redis;

public class DeviceOperationService {

    /**
     * 将数据缓存值Redis，缓存时间120秒，存值方式K，V形式
     * @param key
     * @param value
     */
    public static void cacheOperationInfoById(String key, Object value) {
        Redis.use().setex(key, 120, value);
    }
    /*
        将数据缓存至Redis，存值方式以K,V形式
     */
    public static void cacheOperationInfoById(String key, int second, Object value) {
        Redis.use().setex(key, second, value);
    }
    /*
    从redis中获取存储的内容
     */
    public static Object getOperationInfoById(String key) {

        if ( Redis.use().exists(key) ) {
            return Redis.use().get(key);
        }

        return null;

    }

    public static void updateOperationInfoById(String key, Integer result) {
        if ( Redis.use().exists(key) ) {
            Redis.use().setex(key, 60, result);
        }

    }

    public static void cacheOperationInfoById(String key, Object value, boolean flag) {
        Redis.use().set(key, value);
    }

}
