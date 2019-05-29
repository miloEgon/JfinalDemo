package com.demo.service;

import com.demo.entity.PageRequestBean;
import com.demo.entity.estate.EstateSaveBean;
import com.demo.entity.floor.Floor;
import com.demo.utils.DeanUtils;
import com.demo.utils.EncryptionType;
import com.demo.utils.MD5Util;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EstateService {

    private static final Logger logger = LoggerFactory.getLogger(EstateService.class);

    RoomService roomService = new RoomService();

    public Page<Record> findEstates(PageRequestBean bean) {
        /*Page<Estate> estates = Estate.dao.paginate(pageNum, pageSize, select, sql);
        for (Estate estate : estates.getList()) {
            estate.setCreateDate(DeanUtils.df.format(estate.getCreateDate()));
            estate.setModifyDate(DeanUtils.df.format(estate.getModifyDate()));
        }*/
        Page<Record> estates = Db.paginate(bean.getPageNum(), bean.getPageSize(), "select *", "from tb_estate");
        return estates;
    }

    public Object batchSave(EstateSaveBean bean) {
        Record record = new Record()
                .set("id", MD5Util.encryption(EncryptionType.estate_id+DeanUtils.df.format(new Date())+DeanUtils.getRandom(10000)))
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

    public Record findEstateById(String estate_id) {
//        Record record = Db.findById("tb_estate", estate_id);
        Record record = Db.findFirst("select name,address,id from tb_estate where id = ?", estate_id);
        List<Record> records = Db.find("select name,id from tb_floor where estate_id = ? order by name", estate_id);
        record.set("floorList",records);
        record.set("floors",records.size());
        List<String> floor_ids = new ArrayList<>();
        for (Record r : records) {
            String id = String.valueOf(r.get("id"));
            floor_ids.add("\""+id+"\"");
        }
        record.set("rooms",roomService.countRooms(floor_ids));
        return record;
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
