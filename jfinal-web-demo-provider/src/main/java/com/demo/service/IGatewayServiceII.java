package com.demo.service;

import com.demo.entity.gateway.Gateway;

/**
 * version = 2.0
 * 新定义的api
 * Created by mr.j on 2017/11/10.
 */
public interface IGatewayServiceII {
  // 根据id获取网关
  Gateway getGatewayById(String id);

  // 根据mac获取网关
  Gateway getGatewayByMac(String mac);

  // 根据serial获取网关
  Gateway getGatewayBySerial(String serial);

  // 根据子设备id获取网关
  Gateway getGatewayByDeviceId(String deviceId);

  // 根据UUID获取网关
  Gateway getGatewayByUuid(String uuid);

  // 下发指令
  Object operation(Object user, String operationName, String deviceId, Object parameter);

  // 获取下发指令的结果
  Integer getOperationResult(String cseq);

  //内部调用，更改第三方网关的mq_tag
  boolean updateMqtag(String gatewayid, String mqTag);
}
