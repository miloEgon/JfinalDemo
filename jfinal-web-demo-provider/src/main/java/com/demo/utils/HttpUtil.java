package com.demo.utils;

import com.demo.constant.HttpConstant;
import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.UUID;

/**
 * 该HTTP工具类用于向接口发送请求，该工具类中已封装HTTP我司认证请求头信息，
 * 使用方只需封装请求参数，并调用该工具类的sendRequest方法发送HTTP请求
 * @author hyh
 * @date 2018/05/10
 */
public class HttpUtil {
	
	/**
	 * 封装http基本请求头信息
	 * @param httpUrl：http请求的服务器地址
	 * @param doPost：是否为post，true为post反之get
	 * @return 返回服务器响应消息字符串
	 */
	private static URLConnection setHttpHeader(boolean doPost, String httpUrl) {
		
		String sid = HttpConstant.getSid();
		
		long nowTime = new Date().getTime();
		
		String nonce = UUID.randomUUID().toString();
		
		String sercret = HttpConstant.getSercret();
		
		String sign = MD5Util.encryption(sid + nowTime + nonce + sercret);
		
		URLConnection conn = null;
		
		try {
			URL url = new URL(httpUrl);
			conn = url.openConnection();
			
	        conn.setRequestProperty("accept", "*/*");
	        conn.setRequestProperty("connection", "Keep-Alive");
			if(doPost) {
				conn.setRequestProperty("Charset", CharSetUtil.defCharSet());
				conn.setDoOutput(true);
			}
			
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/json");
			
			//api请求头认证信息
			conn.setRequestProperty("SID", sid);
			conn.setRequestProperty("X-Nonce", nonce);
			conn.setRequestProperty("X-Timestamp", nowTime+"");
			conn.setRequestProperty("X-Sign", sign);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 发起http请求
	 * @param data：待发送的数据
	 * @param url：http请求的服务器地址
	 * @param isPost：是否为post，true为post反之get
	 * @return 返回服务器响应消息字符串
	 * @throws IOException ：IO异常在使用中捕捉，或自行修改该发送请求方法。
	 */
	public static String sendRequest(String data, String url, boolean isPost) throws IOException {
		if(null == url || "".equals(url)) {
			return null;
		}
		
		if(null == data) {
			return null;
		}
		
		URLConnection conn = setHttpHeader(isPost, url);
		byte[] byteData = data.getBytes(CharSetUtil.defCharSet());
		conn.setRequestProperty("Content-length",String.valueOf(byteData));
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(byteData);
		out.flush();
		out.close();
		
		InputStream in = conn.getInputStream();
        String result = new String(IOUtils.toByteArray(in), CharSetUtil.defCharSet());
        in.close();
        return result;
	}
}
