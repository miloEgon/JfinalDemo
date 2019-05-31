package com.demo.service;

import com.demo.entity.gateway.GatewaySaveBean;
import com.demo.entity.gateway.RequestBean;
import com.demo.utils.DeanUtils;
import com.demo.utils.KCode;

import java.util.List;

public class GatewayService {

    public Object addGateway(RequestBean bean) {
        String sql = "insert into `tb_estate_gateway`( `id`, `estate_id`, `gateway_id`, `gateway_name`, `create_date`, `modify_date` ) values ";
        String begin = "( ", prefix = "\"", suffix = "\", ", end = " ),";
        StringBuilder sb = new StringBuilder(sql);
        List<GatewaySaveBean> list = bean.getList();
        for (GatewaySaveBean gateway:list) {
            sb = sb.append( begin+prefix+ KCode.GUID() +suffix
                    +prefix+ bean.getEstate_id() +suffix
                    +prefix+ gateway.getGateway_id() +suffix
                    +prefix+ gateway.getGateway_name() +suffix
                    +"NOW()"+", "+"NOW()"+end );
        }
        return DeanUtils.myBatchSave(sb.substring(0, sb.length() - 1));
    }




}
