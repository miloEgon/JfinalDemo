package com.demo.service;

import com.demo.entity.device.Device;

import java.util.List;

/**
 * Created by mr.j on 2017/11/10.
 */
public interface IDeviceServiceII {
  // 根据id获取硬件信息
  Device getDeviceById(String id);

  // 根据uuid获取硬件信息
  Device getDeviceByUuid(String uuid);

  // 根据mac获取硬件信息
  Device getDeviceByMac(String mac);

  // 根据serial获取硬件信息
  Device getDeviceBySerial(String serial);

  List<Device> getDeviceListByGatewayId(String id);

  List<Device> getDeviceListByGatewayGuuid(String guuid);

  List<Device> getDeviceListByGatewayMac(String mac);

  List<Device> getDeviceListByGatewaySerial(String serial);

  String getDeviceAttr(String id, String key);
}
