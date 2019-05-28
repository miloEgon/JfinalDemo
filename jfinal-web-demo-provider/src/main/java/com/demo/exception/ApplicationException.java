package com.demo.exception;

/**
 * 应用服务专用的异常类
 * Created by Mr.J. on 2017/7/26.
 */
public class ApplicationException extends RuntimeException {
  private int code;
  private String message;
  private Object data;

  public ApplicationException(int code) {
    super(I18nContext.getMessage(code));
    this.code = code;
    this.data = data;
    this.message = super.getMessage();
  }

  public ApplicationException(int code, Object... param) {
    super(I18nContext.getMessage(code, param));
    this.code = code;
    this.data = data;
    this.message = super.getMessage();
  }

  public ApplicationException(String message, int code, Object data) {
    super(message);
    this.code = code;
    this.data = data;
    this.message = message;
  }

  public ApplicationException(String message, Throwable cause, int code, Object data) {
    super(message, cause);
    this.code = code;
    this.data = data;
    this.message = message;
  }

  public ApplicationException(Throwable cause, int code, Object data) {
    super(cause);
    this.code = code;
    this.data = data;
    this.message = cause.getMessage();
  }

  public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, Object data) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.code = code;
    this.data = data;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
