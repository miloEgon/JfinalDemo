package com.demo.controller;

import com.demo.service.GatewayService;
import com.jfinal.core.ActionKey;

/**
 * 网关Controller
 */
public class GatewayController extends BaseController {

    GatewayService service = new GatewayService();

    /**
     * 房产绑定网关
     */
    /*@ActionKey("/gateway/bindGateway")
    public void bindGateway() {
        Map bean = getJson2Bean(Map.class, getInputStreamData());
        boolean flag = service.bindGateway(bean);
        if (flag) OK();
        else ERROR(Secrets.error_status,"绑定失败");
    }*/

    /**
     * 根据房产ID获取网关列表
     */
    @ActionKey("/gateway/findGateways")
    public void findGateways() {
        OK(service.testRPC());
    }

}
