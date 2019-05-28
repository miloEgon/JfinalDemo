package com.demo.entity.req_bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class EstateRequestBean {

    @Length(max=64)
    @NotBlank(message = "房源ID不可为空")
    private String houseId;     //房源id

    @Length(max=100)
    @NotBlank(message = "房源名不可为空")
    private String houseName;   //房源名称

    @Length(max=6)
    private String areaCode;    //房源所在地地区代码

    @Length(max=300)
    @NotBlank(message = "房源地址不可为空")
    private String houseAddress;//房源地址

    @Length(max=64)
    @NotBlank(message = "楼层ID不可为空")
    private String floorId;     //楼层id

    @Length(max=100)
    @NotBlank(message = "楼层名不可为空")
    private String floorName;   //楼层名称

    @Length(max=64)
    @NotBlank(message = "房间ID不可为空")
    private String roomId;      //房间id

    @Length(max=100)
    @NotBlank(message = "房间名称不可为空")
    private String roomName;    //房间名称

    private String roomCode;    //门牌号

    private String roomNote;    //房间备注

    private String sid;         //第三方身份信息

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomNote() {
        return roomNote;
    }

    public void setRoomNote(String roomNote) {
        this.roomNote = roomNote;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
