package com.demo.entity.gateway;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class GatewaySaveBean {

    @Length(max = 64)
    @NotBlank(message = "网关序列号不能为空")
    private String gateway_id; //网关序列号

    @Length(max = 64)
    @NotBlank(message = "网关名称不能为空")
    private String gateway_name; //网关名称

    @NotBlank(message = "自定义登录态不能为空")
    private String authKey; //自定义登录态

    public String getGateway_id() {
        return gateway_id;
    }

    public String getGateway_name() {
        return gateway_name;
    }

    public void setGateway_id(String gateway_id) {
        this.gateway_id = gateway_id;
    }

    public void setGateway_name(String gateway_name) {
        this.gateway_name = gateway_name;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
