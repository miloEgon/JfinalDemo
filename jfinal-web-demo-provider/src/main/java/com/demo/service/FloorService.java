package com.demo.service;

import com.demo.entity.floor.FloorSaveBean;
import com.demo.utils.DeanUtils;
import com.demo.utils.EncryptionType;
import com.demo.utils.MD5Util;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class FloorService {

    public Object save(FloorSaveBean bean) {
        Record record = new Record()
                .set("id", MD5Util.encryption(EncryptionType.floor_id+DeanUtils.df.format(new Date())+DeanUtils.getRandom(10000)))
                .set("name", bean.getName())
                .set("estate_id", bean.getEstate_id())
                .set("master_id", bean.getMaster_id())
                .set("create_date", DeanUtils.getTimeStamp())
                .set("modify_date", DeanUtils.getTimeStamp());
        boolean flag = Db.save("tb_floor", record);
        if (flag) return "新增楼层成功";
        return "新增楼层失败";
    }

    public List<Record> findRooms(Map bean) {
        List<Record> records = Db.find("select id, number from tb_room where floor_id = ? order by number", bean.get("floor_id"));
        return records;
    }
}
