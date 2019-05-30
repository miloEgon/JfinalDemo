package com.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

public class JSONUtil {
	
	/**
	 * 将给定对象转换为JSON字符串
	 * @param object：待转换为JSON字符串对象
	 * @return 返回转换后的JSON字符串
	 */
	public static String toJSONString( Object object ) {
        return JSON.toJSONString(object);
    }
	
	/**
	 * 将JSON字符串转换为给定JAVABean
	 * @param jsonString：JSON字符串
	 * @param clazz：JAVABean.class
	 * @return 返回转换后的JAVABean
	 */
	public static <T>T parse2Bean(String jsonString ,Class<T> clazz) {
		return JSON.parseObject(jsonString, clazz, Feature.UseBigDecimal);
	}
}
