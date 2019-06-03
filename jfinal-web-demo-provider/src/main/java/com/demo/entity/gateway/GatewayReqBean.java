package com.demo.entity.gateway;

import org.hibernate.validator.constraints.NotBlank;

public class GatewayReqBean {

    @NotBlank(message = "网关序列号不能为空")
    private String gatewaySN;

    @NotBlank(message = "自定义登录态不能为空")
    private String authKey; //自定义登录态

    public String getGatewaySN() {
        return gatewaySN;
    }

    public void setGatewaySN(String gatewaySN) {
        this.gatewaySN = gatewaySN;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
