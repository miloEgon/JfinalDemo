package com.demo.entity;

import com.jfinal.plugin.activerecord.Model;

public class DeviceRelation extends Model {

  private String id;

  private String managerId;

  private String subId;

  public String getId() {
    return id;
  }

  public String getManagerId() {
    return managerId;
  }

  public String getSubId() {
    return subId;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setManagerId(String managerId) {
    this.managerId = managerId;
  }

  public void setSubId(String subId) {
    this.subId = subId;
  }
}
