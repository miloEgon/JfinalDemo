package com.demo.entity;

public class CustomerSession {

    private Long customerId;

    private String openId;

    private String sessionKey;

    public Long getCustomerId() {
        return customerId;
    }

    public String getOpenId() {
        return openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
