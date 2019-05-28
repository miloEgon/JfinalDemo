package com.demo.entity;

public class WeChatEntity {

    private String code;

    private String openid;

    private String session_key;

    public void setCode(String code) {
        this.code = code;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getCode() {
        return code;
    }

    public String getOpenid() {
        return openid;
    }

    public String getSession_key() {
        return session_key;
    }
}
