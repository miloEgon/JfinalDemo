package com.demo.constant;

public class HttpConstant {
	
	//测试环境接口调用基础地址
	public final static String TESTBASEURL = "https://testrd.cmentech.com/test_api";
	
	//产品环境接口调用基础地址
	public final static String PROBASEURL = "https://api.cmentech.com/open_api";
	
	//第三方身份id
	private final static String SID = "17d57f573741414baa6310204faca89c";
	
	//第三方密钥
	private final static String SERCRET = "5C35912AE1B0CE449C0539BCF9CD23D3";
	
	/**
	 * 是否为开发模式返回api基础URL
	 * @param isDev：是否为开发模式
	 * @return 返回api基础URL
	 */
	public static String getBaseUrl(boolean isDev) {
		if(isDev) {
			return PROBASEURL;
		}
		return TESTBASEURL;
	}
	
	/**
	 * 调用该方法返回测试环境api基础URL
	 * @return 返回测试环境api基础URL
	 */
	public static String getBaseUrl() {
		return TESTBASEURL;
	}

	public static String getTestbaseurl() {
		return TESTBASEURL;
	}

	public static String getProbaseurl() {
		return PROBASEURL;
	}

	public static String getSid() {
		return SID;
	}

	public static String getSercret() {
		return SERCRET;
	}
}