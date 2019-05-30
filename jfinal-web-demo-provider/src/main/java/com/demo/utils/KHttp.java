package com.demo.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 使用apache httpclient 实现简单的封装
 * Created by Mr.J. on 2017/6/8.
 */
public class KHttp {
  public static String get(String url) throws IOException {
    return get(url, null, null, "UTF-8");
  }

  public static String get(String url, Map<String, String> parameters) throws IOException {
    return get(url, null, parameters, "UTF-8");
  }

  public static String get(String url, Map<String, String> headers, Map<String, String> parameters) throws IOException {
    return get(url, headers, parameters, "UTF-8");
  }

  public static String get(String url, Map<String, String> headers, Map<String, String> parameters, String encoding) throws IOException {
    CloseableHttpClient httpclient = build();
    try {
      if ( parameters != null && parameters.size() > 0 ) {
        String paras = buildParas(parameters, encoding);
        if ( !paras.equals("") ) url += "?" + paras;
      }
      HttpGet httpget = new HttpGet(url);
      httpget.setConfig(getConfig());

      if ( headers != null ) {
        headers.forEach((k, v) -> {
          httpget.setHeader(k, v);
        });
      }
      String responseBody = httpclient.execute(httpget, new MResponseHandler());
      return responseBody;
    } finally {
      try {
        httpclient.close();
      } catch ( IOException e ) {
        e.printStackTrace();
      }
    }
  }

  public static String post(String url) throws IOException {
    return post(url, null, null, "UTF-8", null);
  }

  public static String post(String url, String data) throws IOException {
    return post(url, null, null, "UTF-8", data);
  }

  public static String post(String url, Map<String, String> parameters) throws IOException {
    return post(url, null, parameters, "UTF-8", null);
  }

  public static String post(String url, Map<String, String> headers, Map<String, String> parameters) throws IOException {
    return post(url, headers, parameters, "UTF-8", null);
  }

  public static String post(String url, Map<String, String> headers, Map<String, String> parameters, String data) throws IOException {
    return post(url, headers, parameters, "UTF-8", data);
  }

  public static String post(String url, Map<String, String> headers, Map<String, String> parameters, String encoding, String data) throws IOException {
    CloseableHttpClient httpclient = build();
    try {
      HttpPost httppost = new HttpPost(url);
      httppost.setConfig(getConfig());

      if ( headers != null ) {
        headers.forEach((k, v) -> {
          httppost.setHeader(k, v);
        });
      }

      if ( parameters != null ) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        parameters.forEach((k, v) -> {
          nvps.add(new BasicNameValuePair(k, v));
        });
        httppost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
      }

      if ( data != null ) {
        httppost.setEntity(new StringEntity(data, encoding));
      }

      String responseBody = httpclient.execute(httppost, new MResponseHandler());
      return responseBody;
    } finally {
      try {
        httpclient.close();
      } catch ( IOException e ) {
        e.printStackTrace();
      }
    }
  }

  private static class MResponseHandler implements ResponseHandler<String> {
    @Override
    public String handleResponse(HttpResponse httpResponse) throws IOException {
      int status = httpResponse.getStatusLine().getStatusCode();
      if ( status == 200 ) {
        HttpEntity entity = httpResponse.getEntity();
        return entity != null ? EntityUtils.toString(entity) : null;
      } else if ( status == 302 ) {
        throw new ClientProtocolException("error response status: " + status);
      } else {
        throw new ClientProtocolException("error response status: " + status);
      }
    }
  }

  // 使用默认的配置
  private static RequestConfig getConfig() {
    RequestConfig requestConfig = RequestConfig.custom().
            setConnectTimeout(1000 * 5). // 从连接池获取连接的超时时间
            setConnectionRequestTimeout(1000 * 5). // TCP3次握手的超时时间
            setSocketTimeout(1000 * 10). // read数据的超时时间(这个时间可以长一些)
            setRedirectsEnabled(false).
            build();
    return requestConfig;
  }

  private static CloseableHttpClient build() {
    SSLContext sslcontext = null;
    try {
      sslcontext = createIgnoreVerifySSL();
    } catch ( NoSuchAlgorithmException e ) {
      e.printStackTrace();
    } catch ( KeyManagementException e ) {
      e.printStackTrace();
    }
    // 设置协议http和https对应的处理socket链接工厂的对象
    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("http", PlainConnectionSocketFactory.INSTANCE)
            .register("https", new SSLConnectionSocketFactory(sslcontext))
            .build();
    PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(connManager).build();
    return httpclient;
  }

  private static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
    SSLContext sc = SSLContext.getInstance("SSLv3");
    // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
    X509TrustManager trustManager = new X509TrustManager() {
      @Override
      public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
      }

      @Override
      public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
      }

      @Override
      public X509Certificate[] getAcceptedIssuers() {
        return null;
      }
    };
    sc.init(null, new TrustManager[] { trustManager }, null);
    return sc;
  }

  /**
   * 将参数Map转化为k=v&k=v
   *
   * @param parameters
   * @return
   */
  private static String buildParas(Map<String, String> parameters, String encoding) {
    if ( parameters == null ) return "";
    StringBuilder sb = new StringBuilder();
    parameters.forEach((k, v) -> {
      // continue empty key
      if ( k.equals("") ) return;
      sb.append(k).append("=").append(KWeb.getURLEncoder(v, encoding)).append("&");
    });
    if ( sb.length() == 0 ) return "";
    return sb.substring(0, sb.length() - 1);
  }
}
