package com.demo.entity.gateway;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mr.j on 2017/11/10.
 */
public class Gateway implements Serializable {
  private String id;
  private String manufacturerName;
  private String productName;
  private String serial; //网关序列号
  private String mac; //网关mac地址
  private String proxyNetAddress; //长连接服务器的net地址(ip:port)
  private String securityKey; //网关加密（rsa）的公钥
  private String systemVersion; //网关的软件系统版本号
  private String protocolVersion; //网关网络协议版本号
  private String zigbeeVersion; //网关zigbee模块的版本号
  private Boolean httpsSupported; //网关是否支持https协议
  private Date upgradeDate; //网关升级时间
  private Boolean onlineStatus; //网关状态
  private Date onlineStatusDate; //网关登录时间
  private Integer deviceCount;

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

  public String getProxyNetAddress() {
    return proxyNetAddress;
  }

  public void setProxyNetAddress(String proxyNetAddress) {
    this.proxyNetAddress = proxyNetAddress;
  }

  public String getSecurityKey() {
    return securityKey;
  }

  public void setSecurityKey(String securityKey) {
    this.securityKey = securityKey;
  }

  public String getSystemVersion() {
    return systemVersion;
  }

  public void setSystemVersion(String systemVersion) {
    this.systemVersion = systemVersion;
  }

  public String getProtocolVersion() {
    return protocolVersion;
  }

  public void setProtocolVersion(String protocolVersion) {
    this.protocolVersion = protocolVersion;
  }

  public String getZigbeeVersion() {
    return zigbeeVersion;
  }

  public void setZigbeeVersion(String zigbeeVersion) {
    this.zigbeeVersion = zigbeeVersion;
  }

  public Boolean getHttpsSupported() {
    return httpsSupported;
  }

  public void setHttpsSupported(Boolean httpsSupported) {
    this.httpsSupported = httpsSupported;
  }

  public Date getUpgradeDate() {
    return upgradeDate;
  }

  public void setUpgradeDate(Date upgradeDate) {
    this.upgradeDate = upgradeDate;
  }

  public Boolean getOnlineStatus() {
    return onlineStatus;
  }

  public void setOnlineStatus(Boolean onlineStatus) {
    this.onlineStatus = onlineStatus;
  }

  public Date getOnlineStatusDate() {
    return onlineStatusDate;
  }

  public void setOnlineStatusDate(Date onlineStatusDate) {
    this.onlineStatusDate = onlineStatusDate;
  }

  public Integer getDeviceCount() {
    return deviceCount;
  }

  public void setDeviceCount(Integer deviceCount) {
    this.deviceCount = deviceCount;
  }

  @Override
  public String toString() {
    return "Gateway{" +
            "id='" + id + '\'' +
            ", manufacturerName='" + manufacturerName + '\'' +
            ", productName='" + productName + '\'' +
            ", serial='" + serial + '\'' +
            ", mac='" + mac + '\'' +
            ", proxyNetAddress='" + proxyNetAddress + '\'' +
            ", securityKey='" + securityKey + '\'' +
            ", systemVersion='" + systemVersion + '\'' +
            ", protocolVersion='" + protocolVersion + '\'' +
            ", zigbeeVersion='" + zigbeeVersion + '\'' +
            ", httpsSupported=" + httpsSupported +
            ", upgradeDate=" + upgradeDate +
            ", onlineStatus=" + onlineStatus +
            ", onlineStatusDate=" + onlineStatusDate +
            ", deviceCount=" + deviceCount +
            '}';
  }
}
