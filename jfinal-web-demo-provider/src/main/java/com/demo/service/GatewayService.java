package com.demo.service;

import com.demo.entity.gateway.Gateway;
import com.demo.factory.RpcServiceIIFactory;

public class GatewayService {

    private IGatewayServiceII gatewayService = RpcServiceIIFactory.createGatewayService();

    public Object testRPC() {
        String id = "CXAA17AAA0100417";
        Gateway gateway = gatewayService.getGatewayById(id);
        return gateway;
    }











/*    public boolean bindGateway(Map bean) {
//        ResponseEntity resp = DeanUtils.sendRequest(bean, HttpConstant.getBaseUrl()+"/house/bindGateway");
        ResponseEntity resp = new ResponseEntity(Secrets.success_status, null, Secrets.success_msg);
        if (resp.getCode() == 0) { //绑定成功
            return Db.save("tb_estate_gateway", new Record()
                    .set("id", DeanUtils.getSecretId(EncryptionType.estate_gateway_id))
                    .set("estate_id", bean.get("estateId"))//网关序列号
                    .set("gateway_id", bean.get("gatewaySN"))//房源ID
                    .set("create_date", DeanUtils.getTimeStamp())
                    .set("modify_date", DeanUtils.getTimeStamp())
            );
        }
        return false;
    }*/



}
