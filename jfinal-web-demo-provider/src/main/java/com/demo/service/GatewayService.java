package com.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.entity.gateway.GatewayReqBean;
import com.demo.entity.gateway.GatewaySaveBean;
import com.demo.entity.gateway.RequestBean;
import com.demo.factory.RpcServiceIIFactory;
import com.demo.utils.DeanUtils;
import com.demo.utils.KCode;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.List;

public class GatewayService {

//    private IGatewayServiceII gatewayService = RpcServiceIIFactory.createGatewayService();

    public Object verifyUnique(RequestBean bean) {
        //内部判重
        List<GatewaySaveBean> list = bean.getList();
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String gatewaySN = list.get(i).getGateway_id();
            if (!list2.contains(gatewaySN)) {
                list2.add(gatewaySN);
            }
        }
        if (list.size() != list2.size()) return "存在重复的网关";
        //连库判重
        StringBuilder sql = new StringBuilder("SELECT COUNT(1) count FROM tb_estate_gateway WHERE gateway_id IN ( ");
        for (String id:list2) {
            sql = sql.append("\""+id+"\",");
        }
        sql = sql.replace(sql.length()-1, sql.length(), " )");
        List<Record> records = Db.find(sql.toString());
        if ((long)records.get(0).get("count") > 0) return "存在重复的网关";
        return null;
    }

    public Object addGateway(RequestBean bean) {
        //判重
        Object unique = verifyUnique(bean);
        if (null != unique) return unique;
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

    public Object findGateways(RequestBean bean) {
        List<Record> records = Db.find("SELECT gateway_id, gateway_name FROM tb_estate_gateway WHERE estate_id = ?",
                bean.getEstate_id());
        /*List<Object> list = new ArrayList<>();
        for (Record record : records) {
            list.add(record.toJson());
        }*/
        JSONObject data = new JSONObject();
        data.put("data", records);
        return data;
    }

    public Boolean modifyGatewayName(GatewaySaveBean bean) {
        return Db.update("UPDATE tb_estate_gateway SET gateway_name = ?, modify_date = NOW() WHERE gateway_id = ?",
                bean.getGateway_name(), bean.getGateway_id()) > 0;
    }

    public Boolean removeGateway(GatewayReqBean bean) {
        return Db.delete("DELETE FROM tb_estate_gateway WHERE gateway_id = ?",
                bean.getGatewaySN()) > 0;
    }

//    public Object getGatewayById(GatewayReqBean bean) {
//        return gatewayService.getGatewayById(bean.getGatewaySN());
//    }










}
