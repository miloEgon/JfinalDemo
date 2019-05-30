package com.demo.entity.device;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mr.j on 2017/11/10.
 */
public class Device implements Serializable {
  private String id;
  private String manufacturerName;
  private String productName;
  private String serial;
  private String mac;
  private Integer deviceType;
  private Integer battery;
  private String gatewayId;
  private String uuid;
  private Boolean onlineStatue;
  private Boolean onNetworkStatue;
  private Date onlineStatueDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getManufacturerName() {
    return manufacturerName;
  }

  public void setManufacturerName(String manufacturerName) {
    this.manufacturerName = manufacturerName;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }

  public Integer getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(Integer deviceType) {
    this.deviceType = deviceType;
  }

  public Integer getBattery() {
    return battery;
  }

  public void setBattery(Integer battery) {
    this.battery = battery;
  }

  public String getGatewayId() {
    return gatewayId;
  }

  public void setGatewayId(String gatewayId) {
    this.gatewayId = gatewayId;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Boolean getOnlineStatue() {
    return onlineStatue;
  }

  public void setOnlineStatue(Boolean onlineStatue) {
    this.onlineStatue = onlineStatue;
  }

  public Boolean getOnNetworkStatue() {
    return onNetworkStatue;
  }

  public void setOnNetworkStatue(Boolean onNetworkStatue) {
    this.onNetworkStatue = onNetworkStatue;
  }

  public Date getOnlineStatueDate() {
    return onlineStatueDate;
  }

  public void setOnlineStatueDate(Date onlineStatueDate) {
    this.onlineStatueDate = onlineStatueDate;
  }

  @Override
  public String toString() {
    return "Device{" +
            "id='" + id + '\'' +
            ", manufacturerName='" + manufacturerName + '\'' +
            ", productName='" + productName + '\'' +
            ", serial='" + serial + '\'' +
            ", mac='" + mac + '\'' +
            ", deviceType=" + deviceType +
            ", battery=" + battery +
            ", gatewayId='" + gatewayId + '\'' +
            ", uuid='" + uuid + '\'' +
            ", onlineStatue=" + onlineStatue +
            ", onNetworkStatue=" + onNetworkStatue +
            ", onlineStatueDate=" + onlineStatueDate +
            '}';
  }
}
