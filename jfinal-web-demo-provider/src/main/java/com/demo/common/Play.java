package com.demo.common;

import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;

public class Play {
  private static final String play = "play.properties";

  public static boolean isLocal() {
    return getProperty(Dict.CONFIG_PMR_MODE).equals("local");
  }

  public static boolean isNetWork() {
    return getProperty(Dict.CONFIG_PMR_MODE).equals("network");
  }

  public static boolean isDebug() {
    return getProperty(Dict.CONFIG_AUT_MODE).equals("debug");
  }

  public static boolean isSSO() {
    return getProperty(Dict.CONFIG_AUT_MODE).equals("sso");
  }

  public static boolean isNone() {
    return getProperty(Dict.CONFIG_AUT_MODE).equals("none");
  }

  public static boolean isNormal() {
    return getProperty(Dict.CONFIG_AUT_MODE).equals("normal");
  }

  public static boolean isJFinalDebug() {
    return getProperty(Dict.CONFIG_JFINAL_MODE).equals("true");
  }

  public static boolean isServiceDebug() {
    return getProperty(Dict.CONFIG_SERVICE_MODE).equals("true");
  }

  public static String getProperty(String key) {
    return PropKit.use(play, "UTF-8").get(key);
  }

  public static String getProperty(String key, String defaultValue) {
    String value = getProperty(key);
    return StrKit.isBlank(value) ? defaultValue : value;
  }

  public static Integer getPropertyToInt(String key) {
    return PropKit.use(play, "UTF-8").getInt(key);
  }

  public static Integer getPropertyToInt(String key, Integer defaultValue) {
    Integer value = getPropertyToInt(key);
    return value == null ? defaultValue : value;
  }

}
