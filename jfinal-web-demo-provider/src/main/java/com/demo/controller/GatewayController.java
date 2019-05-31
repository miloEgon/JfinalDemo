package com.demo.controller;

import com.demo.entity.gateway.GatewaySaveBean;
import com.demo.entity.gateway.RequestBean;
import com.demo.service.GatewayService;
import com.jfinal.core.ActionKey;

import java.util.List;

/**
 * 网关Controller
 */
public class GatewayController extends BaseController {

    GatewayService service = new GatewayService();

    @ActionKey("/gateway/addGateway")
    public void addGateway() {
        RequestBean requestBean = getJson2Bean(RequestBean.class, getInputStreamData());
        JSR303Validator(requestBean);
        List<GatewaySaveBean> list = requestBean.getList();
        for (GatewaySaveBean bean:list) {
            JSR303Validator(bean);
        }
        OK(service.addGateway(requestBean));
    }


}
