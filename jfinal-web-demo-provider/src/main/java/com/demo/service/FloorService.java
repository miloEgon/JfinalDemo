package com.demo.service;

import com.demo.entity.floor.FloorSaveBean;
import com.demo.utils.DeanUtils;
import com.demo.utils.EncryptionType;
import com.demo.utils.KCode;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FloorService {

    public Object save(FloorSaveBean bean) {
        /*Record record = new Record()
                .set("id", DeanUtils.getSecretId(EncryptionType.floor_id))
                .set("name", bean.getName())
                .set("estate_id", bean.getEstate_id())
                .set("master_id", bean.getMaster_id())
                .set("create_date", DeanUtils.getTimeStamp())
                .set("modify_date", DeanUtils.getTimeStamp());
        boolean flag = Db.save("tb_floor", record);
        if (flag) return "新增楼层成功";
        return "新增楼层失败";*/

        String sql = " insert into `tb_floor`(`master_id`, `name`, `id`, `create_date`, `modify_date`, `estate_id`) values ";
        String begin = "( ", prefix = "\"", suffix = "\", ", end = " ), ";
        StringBuilder sb = new StringBuilder(sql);
        for (int i = 1; i <= 5; i++) {
            sb = sb.append( begin+prefix+bean.getMaster_id()+suffix
                    +prefix+"0"+i+"楼"+suffix
                    +prefix+DeanUtils.getSecretId(EncryptionType.floor_id)+suffix
                    +"NOW()"+", "
                    +"NOW()"+", "
                    +prefix+bean.getEstate_id()+prefix+end );
        }
        return DeanUtils.myBatchSave(sb.substring(0, sb.length() - 2));
    }

    public static void main(String[] args) {
        System.out.println(new FloorService().getFloorNum("04楼"));
    }

    public String getFloorNum(String name) {
        char c = name.charAt(0);
        if (c == '0') {
            return name.substring(1, name.length() - 1);
        } else {
            return name.substring(0, name.length() - 1);
        }
    }

    public Object saveRooms(FloorSaveBean bean) {
        String sql = " insert into `tb_room`(`master_id`, `name`, `id`, `create_date`, `modify_date`, `floor_id`) values ";
        String begin = "( ", prefix = "\"", suffix = "\", ", end = " ), ";
        StringBuilder sb = new StringBuilder(sql);
        //关键是获取floor_id的集合
        List<Record> records = Db.find("SELECT id,name FROM tb_floor WHERE estate_id = ?", bean.getEstate_id());
        for (Record record : records) {
            for (int i = 1; i <= 5; i++) {
                sb = sb.append( begin+prefix+bean.getMaster_id()+suffix
                        +prefix+getFloorNum(record.get("name"))+"0"+i+suffix
                        +prefix+ KCode.GUID() +suffix
                        +"NOW()"+", "
                        +"NOW()"+", "
                        +prefix+record.get("id")+prefix+end );
            }
        }
        return DeanUtils.myBatchSave(sb.substring(0, sb.length() - 2));
    }

    /*public boolean myBatchSave(String sql) {
        Connection conn = null;
        Statement stat = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            PropKit.use("play.properties");
            conn = DriverManager.getConnection(PropKit.get("jdbc.url"), PropKit.get("jdbc.user"), PropKit.get("jdbc.pass"));
            stat = conn.createStatement();
            return stat.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }*/





    public List<Record> findRooms(Map bean) {
        List<Record> records = Db.find("select id, number from tb_room where floor_id = ? order by number", bean.get("floor_id"));
        return records;
    }
}
