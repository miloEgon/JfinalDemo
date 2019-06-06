package com.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.entity.gateway.GatewayBean;
import com.demo.entity.gateway.GatewayReqBean;
import com.demo.entity.gateway.GatewaySaveBean;
import com.demo.entity.gateway.RequestBean;
import com.demo.exception.ApplicationException;
import com.demo.utils.DeanUtils;
import com.demo.utils.KCode;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.List;

public class GatewayService {

//    private IGatewayServiceII gatewayService = RpcServiceIIFactory.createGatewayService();

//    public Object getRoomsByGateway(GatewayReqBean bean) {
//        List<DeviceRelation> deviceId = gatewayService.getDeviceId(bean.getGatewaySN());
//        String sql = "SELECT tr.id, tr.`name` FROM tb_room_device trd, tb_room tr WHERE device_id IN ( ";
//        for (DeviceRelation device:deviceId) {
//            sql = sql + "\""+device.getSubId()+"\",";
//        }
//        List<Record> records = Db.find(sql.substring(0, sql.length() - 1) + " ) AND bind_status = \"BIND\" AND tr.id = trd.room_id");
//        JSONObject data = new JSONObject();
//        data.put("data",records);
//        return data;
//    }

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

    public void permitJoinCheck(GatewayBean bean, String appId, String authKey) {
        Record record = Db.findById("tb_room",bean.getRoomId());
        if(record == null) {
            throw new ApplicationException("房间不存在，请核查",-1,null);
        }
        if (null == DeviceOperationService.getOperationInfoById("openid:"+authKey)) {
            if( !record.get("master_id").equals(appId) ) {
                throw new ApplicationException("对不起，您无权操作该房间",-1,null);
            }
        }
        record = Db.findFirst("SELECT id, device_id, bind_status FROM tb_room_device WHERE room_id = ?",
                bean.getRoomId());
        if(record == null) {//无绑定记录
            return;
        }
        if("BIND".equals(record.getStr("bind_status"))) {
            throw new ApplicationException("房间已绑定，请勿重复绑定",-1,null);
        }
    }

    public void executePermitJoin(GatewayBean bean, String sid, String authKey) {
//        Record record = null;
        /*if (null == DeviceOperationService.getOperationInfoById("openid:"+authKey)){
            record = userService.getCompany(sid);
            if(null == record) {
                throw new ApplicationException("您的身份信息无效",-1,null);
            }
            callUrl = record.getStr("callback_url");
        }*/

        /*String tempUuid = KCode.GUID();
        //如果正常下发，则返回cseq操作结果
        String result = (String)gatewayService.operation(sid, "PermitJoin", bean.getGatewaySN(), tempUuid);

        DeviceOperationService.cacheOperationInfoById(tempUuid, bean.getRoomId());
        DeviceOperationService.cacheOperationInfoById(result, -999);

        ExecutorPool.execute(new PermitJoinPush(result, callUrl,
                KDate.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
                bean.getGatewaySN()));*/

    }










}
