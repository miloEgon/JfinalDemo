package com.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.entity.PageEntity;
import com.demo.entity.ReqBean;
import com.demo.entity.estate.EstateSaveBean;
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
        /*Record record = Db.findFirst("select `name`,address,master_id from tb_estate where id = ?", bean.getEstate_id());

        Record master_id = Db.findFirst("SELECT count(*) as sums FROM `tb_floor` " +
                " where master_id = ?", record.getStr("master_id"));

        List<Record> records = Db.find("select `name`,id from tb_floor where estate_id = ? order by `name`",
                bean.getEstate_id());

        record.remove("master_id");
        record.set("floorList",records);
        record.set("floors",records.size());

        record.set("rooms",master_id.getLong("sums"));

        JSONObject data = new JSONObject();
        data.put("data", record);
        return data;*/
    }

    public Object findRooms(Map bean) {
        List<Record> records = Db.find("select id, name from tb_room where floor_id = ? order by name", bean.get("floor_id"));
        /*List<Object> list = new ArrayList<>();
        for (Record record : records) {
            list.add(record.toJson());
        }
        return list;*/
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
