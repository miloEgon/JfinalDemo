package com.demo.factory;

import com.demo.common.RpcKit;
import com.demo.service.IGatewayServiceII;

/**
 * version = 2.0
 * Created by Mr.J. on 2017/8/16.
 */
public class RpcServiceIIFactory {
//  public static IDeviceServiceII createDeviceService() {
//    return RpcKit.createProxy("adam_device_rpc_service", IDeviceServiceII.class);
//  }
//
//  public static IGatewayServiceII createGatewayService() {
//    return RpcKit.createProxy("adam_device_rpc_service", IGatewayServiceII.class);
//  }

//  public static IDeviceServiceII createDeviceService() {
//    return RpcKit.createProxy("device_rpc_service", IDeviceServiceII.class);
//  }

  public static IGatewayServiceII createGatewayService() {
    return RpcKit.createProxy("device_rpc_service", IGatewayServiceII.class);
  }
}
