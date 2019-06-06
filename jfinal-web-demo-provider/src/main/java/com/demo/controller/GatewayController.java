package com.demo.controller;

import com.demo.entity.gateway.GatewayBean;
import com.demo.entity.gateway.GatewayReqBean;
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

    /**
     * 扫码或手动录入网关信息
     */
    @ActionKey("/gateway/addGateway")
    public void addGateway() {
        RequestBean requestBean = getJson2Bean(RequestBean.class, getInputStreamData());
        requestBean.setAuthKey(getHeader("X-Auth"));
        JSR303Validator(requestBean);
        List<GatewaySaveBean> list = requestBean.getList();
        for (GatewaySaveBean bean:list) {
            bean.setAuthKey(getHeader("X-Auth"));
            JSR303Validator(bean);
        }
        OK(service.addGateway(requestBean));
    }

    /**
     * 根据房产ID获取网关列表
     */
    @ActionKey("/gateway/findGateways")
    public void findGateways() {
        RequestBean bean = getJson2Bean(RequestBean.class, getInputStreamData());
        bean.setAuthKey(getHeader("X-Auth"));
        JSR303Validator(bean);
        OK(service.findGateways(bean));
    }

    /**
     * 根据网关序列号获取网关详情
     */
    @ActionKey("/gateway/getGatewayById")
    public void getGatewayById() {
        GatewayReqBean bean = getJson2Bean(GatewayReqBean.class, getInputStreamData());
        bean.setAuthKey(getHeader("X-Auth"));
        JSR303Validator(bean);
        OK("OK");
    }

    /**
     * 更改网关名称
     */
    @ActionKey("/gateway/modifyGatewayName")
    public void modifyGatewayName() {
        GatewaySaveBean bean = getJson2Bean(GatewaySaveBean.class, getInputStreamData());
        bean.setAuthKey(getHeader("X-Auth"));
        JSR303Validator(bean);
        OK(service.modifyGatewayName(bean));
    }

    /**
     * 删除网关
     */
    @ActionKey("/gateway/removeGateway")
    public void removeGateway() {
        GatewayReqBean bean = getJson2Bean(GatewayReqBean.class, getInputStreamData());
        bean.setAuthKey(getHeader("X-Auth"));
        JSR303Validator(bean);
        OK(service.removeGateway(bean));
    }

    /**
     * 网关开启设备入网
     */
    @ActionKey("/gateway/permitJoin")
    public void permitJoin() {
        String info = getInputStreamData();
        GatewayBean bean = getJson2Bean(GatewayBean.class, info);
        JSR303Validator(bean);
        String sid = getHeader("SID");
        String authKey = getHeader("X-Auth");

        service.permitJoinCheck(bean, sid, authKey);
        service.executePermitJoin(bean, sid, authKey);
        OK();
    }





}
