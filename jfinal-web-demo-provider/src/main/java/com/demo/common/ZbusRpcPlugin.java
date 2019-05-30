package com.demo.common;

import com.demo.service.RpcService;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.IPlugin;
import io.zbus.mq.Broker;
import io.zbus.mq.Protocol;
import io.zbus.rpc.bootstrap.mq.ServiceBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.J. on 2017/8/3.
 */
public class ZbusRpcPlugin implements IPlugin {
  Logger LOG = LoggerFactory.getLogger(getClass());
  private static volatile boolean isStart = false;
  // broker是一个重量级别对象(维护了连接Broker服务器的长连接)
  private static Broker broker;
  // 保存topic和class的关系
  private static Map<String, List<Class>> rpcServiceMap = new HashMap<>();

  private String zbusHost;
  private String topic;

  public ZbusRpcPlugin(String zbusHost) {
    if ( StrKit.isBlank(zbusHost) )
      throw new IllegalArgumentException();
    this.zbusHost = zbusHost;
  }

  public static Broker getBroker() {
    return broker;
  }

  public static boolean isIsStart() {
    return isStart;
  }

  @Override
  public boolean start() {
    synchronized ( this ) {
      if ( isStart ) return true;
      isStart = true;
    }
    try {
      broker = new Broker(zbusHost);
      // 全局扫描获取所有class
      List<File> classes = RetroSnaker.getInstance().getFileSetByEndName(PathKit.getRootClassPath(), "class");
      classes.forEach(file -> {
        Class clazz = RetroSnaker.getInstance().getClass(PathKit.getRootClassPath(), file.getPath());
        for ( Annotation annotation : clazz.getAnnotations() ) {
          if ( annotation instanceof RpcService ) {
            String topic = ( (RpcService) annotation ).topic();
            List<Class> serviceList = rpcServiceMap.get(topic);
            if ( serviceList == null ) {
              serviceList = new ArrayList<>();
            }
            serviceList.add(clazz);
            rpcServiceMap.put(topic, serviceList);
          }
        }
      });
      // init zbus consumer
      rpcServiceMap.forEach((topic, serviceList) -> {
        if ( serviceList == null || serviceList.size() == 0 ) return;
        ServiceBootstrap b = new ServiceBootstrap();
        serviceList.forEach( service -> {
          b.addModule(service);
        });
        try {
          b.serviceName(topic)
                  .serviceAddress(zbusHost)
                  .serviceMask(Protocol.MASK_RPC|Protocol.MASK_MEMORY)
                  .responseTypeInfo(true)
                  .connectionCount(2)
                  .start();
        } catch ( Exception e ) {
          throw new Error(e);
        }
      });
    } catch ( Exception e ) {
      e.printStackTrace();
      isStart = false;
      stop();
    }
    return isStart;
  }

  @Override
  public boolean stop() {
    try {
      if ( broker != null )
        broker.close();
    } catch ( IOException e ) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    new ZbusRpcPlugin("172.16.73.2:15566").start();
  }
}
