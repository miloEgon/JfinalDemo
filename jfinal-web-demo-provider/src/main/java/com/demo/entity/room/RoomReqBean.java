package com.demo.entity.room;

import org.hibernate.validator.constraints.NotBlank;

public class RoomReqBean {

    @NotBlank(message = "楼层ID不能为空")
    private String floor_id;

    @NotBlank(message = "自定义登录态不能为空")
    private String authKey;

    public String getFloor_id() {
        return floor_id;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setFloor_id(String floor_id) {
        this.floor_id = floor_id;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
