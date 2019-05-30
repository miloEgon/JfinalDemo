package com.demo.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * A Web helper tools
 *
 * @author Mr.J.(slashchenxiaojun@sina.com)
 * @version 1.0
 * @date 2015-03-28
 **/
public class KWeb {

  private static Location getLocation(HttpServletRequest request) {
    Location location = new Location();
    location.hash = "";
    location.search = request.getQueryString() == null ? "" : "?" + request.getQueryString();
    location.protocol = request.getScheme();
    location.hostname = request.getServerName();
    location.pathname = request.getRequestURI();
    location.port = request.getServerPort() == 80 ? "" : request.getServerPort() + "";
    location.host = location.hostname + ( location.port.equals( "" ) ? "" : ":" + location.port );
    location.origin = location.protocol + "://" + location.host + request.getContextPath();
    location.href = location.origin + location.pathname + location.search + location.hash;
    return location;
  }

  /**
   * 获取绝对全局路径
   */
  public static String getPath(HttpServletRequest request) {
    Location location = getLocation( request );
    return location.origin;
  }

  /**
   * 获取现有URI字符串(包含servletPath)，包括请求参数(多个参数用','分割)
   */
  public static String getPathAndParamter(HttpServletRequest request) {
    Location location = getLocation( request );
    return location.href;
  }

  /**
   * URL反编码
   *
   * @param url
   * @param enc 编码
   * @throws UnsupportedEncodingException
   */
  public static String getURLDecoder(String url, String enc) {
    try {
      return URLDecoder.decode( url, enc );
    } catch ( UnsupportedEncodingException e ) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * URL编码
   *
   * @param url
   * @param enc 编码
   * @throws UnsupportedEncodingException
   */
  public static String getURLEncoder(String url, String enc) {
    try {
      return URLEncoder.encode( url, enc );
    } catch ( UnsupportedEncodingException e ) {
      e.printStackTrace();
    }
    return null;
  }

  public static String getCookie(HttpServletRequest request, String name) {
    return getCookie( request, name, null );
  }

  public static String getCookie(HttpServletRequest request, String name, String defaultValue) {
    Cookie cookie = getCookieObject( request, name );
    return cookie != null ? cookie.getValue() : defaultValue;
  }

  public static Cookie getCookieObject(HttpServletRequest request, String name) {
    Cookie[] cookies = request.getCookies();
    if ( cookies != null )
      for ( Cookie cookie : cookies )
        if ( cookie.getName().equals( name ) )
          return cookie;
    return null;
  }

  public static void setCookie(HttpServletResponse response, String name, String value) {
    doSetCookie( response, name, value, -1, null, null, null );
  }

  public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
    doSetCookie( response, name, value, maxAgeInSeconds, null, null, null );
  }

  public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds, String domain) {
    doSetCookie( response, name, value, maxAgeInSeconds, null, domain, null );
  }

  private static void doSetCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds, String path, String domain, Boolean isHttpOnly) {
    Cookie cookie = new Cookie( name, value );
    cookie.setMaxAge( maxAgeInSeconds );
    // set the default path value to "/"
    if ( path == null ) {
      path = "/";
    }
    cookie.setPath( path );

    if ( domain != null ) {
      cookie.setDomain( domain );
    }
//    if (isHttpOnly != null) {
//      cookie.setHttpOnly(isHttpOnly);
//    }
    response.addCookie( cookie );
  }

  /**
   * 获取代理服务器ip
   *
   * @param request
   * @return
   */
  public static String getProxyIp(HttpServletRequest request) {
    String realIPKey = "X-Real-IP";
    String forwardedForKey = "X-Forwarded-For";
    String proxyClientIPKey = "Proxy-Client-IP";
    String wlProxyClientIPKey = "WL-Proxy-Client-IP";
    String ip = request.getHeader( realIPKey );
    if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) ) {
      ip = request.getHeader( forwardedForKey );
    }
    if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) ) {
      ip = request.getHeader( proxyClientIPKey );
    }
    if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) ) {
      ip = request.getHeader( wlProxyClientIPKey );
    }
    if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) ) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }
}

// pojo location bean like js window.location obj
class Location {
  String hash;
  String host;
  String hostname;
  String href;
  String origin;
  String pathname;
  String port;
  String protocol;
  String search;
}