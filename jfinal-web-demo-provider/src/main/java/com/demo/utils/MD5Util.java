package com.demo.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 该MD5工具类用于调用HTTP进行发送请求时，对请求头相关信息进行加密。
 * 该工具类中的字符编码均采用utf-8
 * @author hyh
 * @date 2018/05/10
 */

public class MD5Util {
	
	/**
	 * 对给定字符串进行MD5加密
	 * @param encode：待加密的字符串
	 * @return 返回加密后的MD5字符串
	 */
	public static String encryption(String encode) {
		return DigestUtils.md5Hex(encode);
	}
}
