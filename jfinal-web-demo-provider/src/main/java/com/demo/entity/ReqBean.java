package com.demo.entity;

import org.hibernate.validator.constraints.NotBlank;

public class ReqBean {

    @NotBlank(message = "房产ID不能为空")
    private String estate_id;

    @NotBlank(message = "自定义登录态不能为空")
    private String authKey;

    public String getEstate_id() {
        return estate_id;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setEstate_id(String estate_id) {
        this.estate_id = estate_id;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

}
