package com.demo.exception;

import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;

/**
 * 国际化适配器
 * Created by Mr.J. on 2017/7/27.
 */
public final class I18nContext {
  public static Res IL8N = I18n.use("zh_CN");

  public static String getMessage(Object key) {
    return IL8N.get(key.toString());
  }

  public static String getMessage(Object key, Object... param) {
    return IL8N.format(key.toString(), param);
  }
}
