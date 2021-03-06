package com.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.entity.PageEntity;
import com.demo.entity.ReqBean;
import com.demo.entity.estate.EstateSaveBean;
import com.demo.entity.room.RoomReqBean;
import com.demo.exception.ApplicationException;
import com.demo.utils.DeanUtils;
import com.demo.utils.EncryptionType;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EstateService {

    RoomService roomService = new RoomService();

    public Object getEstateList(String authKey) {
        String openid = (String)DeviceOperationService.getOperationInfoById("openid:" + authKey);
        if (StrKit.isBlank(openid))
            throw new ApplicationException("身份验证失败",-1,null);
        List<Record> records = Db.find("select te.id, te.name from tb_estate te, tb_wechat_user wu where wu.open_id = ? and te.master_id = wu.sid order by te.modify_date desc",
                openid);
        JSONObject data = new JSONObject();
        data.put("data", records);
        return data;
    }

    public Object findEstates(PageEntity bean) {
        String openid = (String)DeviceOperationService.getOperationInfoById("openid:" + bean.getAuthKey());
        if (StrKit.isBlank(openid))
            throw new ApplicationException("身份验证失败",-1,null);
        Page<Record> estates = Db.paginate(
                bean.getPageNum(),
                bean.getPageSize(),
                "select te.id, te.name ",
                "from tb_estate te, tb_wechat_user wu where wu.open_id = ? and te.master_id = wu.sid order by te.modify_date desc", openid);
        Record record = new Record()
                .set("list",estates.getList())
                .set("pageNumber",estates.getPageNumber())
                .set("pageSize",estates.getPageSize())
                .set("totalPage",estates.getTotalPage())
                .set("totalRow",estates.getTotalRow());
        return record.toJson();
    }

    public Object findEstateById(ReqBean bean) {
        Record record = Db.findFirst("select name,address,id from tb_estate where id = ?", bean.getEstate_id());
        List<Record> records = Db.find("select name,id from tb_floor where estate_id = ? order by name", bean.getEstate_id());
        record.set("floorList",records);
        record.set("floors",records.size());
        List<String> floor_ids = new ArrayList<>();
        for (Record r : records) {
            String id = r.get("id");
            floor_ids.add("\""+id+"\"");
        }
        if (floor_ids.size()>0)
            record.set("rooms",roomService.countRooms(floor_ids));

        JSONObject data = new JSONObject();
        data.put("data", record);
        return data;
    }

    public List<String> findBindRooms(List<String> rooms) {
        StringBuilder sql = new StringBuilder("SELECT room_id FROM tb_room_device WHERE room_id IN ( ");
        for (String room:rooms) {
            sql = sql.append(room+",");
        }
        sql = sql.replace(sql.length()-1, sql.length(), " )");
        List<String> ids = new ArrayList<>();
        List<Record> records = Db.find(sql.toString());
        for (Record r:records) {
            ids.add(r.get("room_id"));
        }
        return ids;
    }

    public Object findRooms(RoomReqBean bean) {
        List<Record> records = Db.find("select id, name from tb_room where floor_id = ? order by name", bean.getFloor_id());
        List<String> room_ids = new ArrayList<>();
        for (Record r: records) {
            room_ids.add("\""+r.get("id")+"\"");
        }
        if (room_ids.size()>0) {
            List<String> bindRooms = findBindRooms(room_ids);
            for (Record r: records) {
                if (bindRooms.contains(r.get("id"))) {
                    r.set("status",1);
                } else {
                    r.set("status",0);
                }
            }
        }
        JSONObject data = new JSONObject();
        data.put("data", records);
        return data;
    }









    public Object batchSave(EstateSaveBean bean) {
        Record record = new Record()
                .set("id", DeanUtils.getSecretId(EncryptionType.estate_id))
                .set("name", bean.getName())
                .set("area_code", bean.getArea_code())
                .set("address", bean.getAddress())
                .set("master_id", bean.getMaster_id())
                .set("create_date", DeanUtils.getTimeStamp())
                .set("modify_date", DeanUtils.getTimeStamp());
        boolean flag = Db.save("tb_estate", record);
        if (flag) return "新增房产成功";
        return "新增房产失败";
    }























     /*Estate estate = Estate.dao.findById(estate_id);
        estate.setCreateDate(DeanUtils.df.format(estate.getCreateDate()));
        estate.setModifyDate(DeanUtils.df.format(estate.getModifyDate()));
        String sql = "select * from tb_floor where estate_id = ?";
        List<Floor> floors = Floor.dao.find(sql,estate_id);
        for (Floor floor : floors) {
            floor.setCreateDate(DeanUtils.df.format(floor.getCreateDate()));
            floor.setModifyDate(DeanUtils.df.format(floor.getModifyDate()));
        }
        estate.setFloorList(floors);
        estate.setFloors(floors.size());

        return estate;*/

    /*public Object batchSave(String sql, String columns, List modelList, int batchSize) {

        int[] result = Db.batch(sql, columns, modelList, batchSize);
        return result;

        // 判断list的大小,如果大于200条,则size/200分批次保存
        int size = modelList.size();
        if (200 >= size) {
            Db.batchSave(modelList, size);
            return "OK";
        }
        int serial = size / 200;
        for (int i = 0; i < serial; i++) {
            Db.batchSave(modelList.subList(i * 200, i * 200 + 200), 200);
        }
        int remain = size - serial * 200;
        Db.batchSave(modelList.subList(serial * 200, size), remain);
        return "OK";
    }*/

}
