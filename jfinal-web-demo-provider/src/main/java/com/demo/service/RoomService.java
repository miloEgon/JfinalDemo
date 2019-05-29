package com.demo.service;

import com.demo.entity.room.Room;
import com.demo.utils.DeanUtils;
import com.demo.utils.EncryptionType;
import com.demo.utils.MD5Util;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomService {

    public Object save(List<Room> list) {
        Record record = new Record();
        boolean flag = false;
        for (Room bean:list) {
            record.set("id", MD5Util.encryption(EncryptionType.room_id+ DeanUtils.df.format(new Date())+DeanUtils.getRandom(10000)))
                    .set("number", bean.getNumber())
                    .set("floor_id", bean.getFloor_id())
                    .set("master_id", bean.getMaster_id())
                    .set("create_date", DeanUtils.getTimeStamp())
                    .set("modify_date", DeanUtils.getTimeStamp());
            flag = Db.save("tb_room", record);
            if (!flag) {
                return "开房失败";
            }
        }
        return "开房成功";
    }

    public Object countRooms(List<String> floors) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) as count FROM tb_room WHERE floor_id in ( ");
        for (String floor:floors) {
            sql = sql.append(floor+",");
        }
        sql = sql.replace(sql.length()-1,sql.length(), " )");

        List<Record> records = Db.find(sql.toString());
        return records.get(0).get("count");
    }

    /*public static void main(String[] args) {
        List<String> floors = new ArrayList<>();
        floors.add("09849f1b00adedae57e018ea56c1c1eb");
        floors.add("034344209a08060b8b64455cc4eb9d36");
        floors.add("8cb5e516240b94c257393b4fbbca5e66");
        floors.add("266beb415c2e4fe7b810a1a32d2aedf5");

        countRooms(floors);
    }*/




}
