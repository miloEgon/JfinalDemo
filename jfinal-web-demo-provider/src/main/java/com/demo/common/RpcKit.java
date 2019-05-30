package com.demo.common;

import io.zbus.mq.Broker;
import io.zbus.rpc.bootstrap.mq.ClientBootstrap;

/**
 * Created by Mr.J. on 2017/8/3.
 */
public class RpcKit {
  public static <T> T createProxy(String topic, Class<T> clazz) {
    Broker broker = ZbusRpcPlugin.getBroker();
    if ( broker == null ) throw new Error("必须启动ZbusRpcPlugin");
    ClientBootstrap b = new ClientBootstrap();
    b.broker(broker);
    b.serviceName(topic);
    return b.createProxy(clazz);
  }
}
