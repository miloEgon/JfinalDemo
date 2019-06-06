package com.demo.entity.gateway;

import org.hibernate.validator.constraints.NotBlank;

public class GatewayBean {

    @NotBlank(message = "网关序列号不能为空")
    private String gatewaySN;

    @NotBlank(message = "房间id不能为空")
    private String roomId;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getGatewaySN() {
        return gatewaySN;
    }

    public void setGatewaySN(String gatewaySN) {
        this.gatewaySN = gatewaySN;
    }
}
