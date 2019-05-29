package com.demo.entity.room;

import com.jfinal.plugin.activerecord.Model;

import java.sql.Timestamp;

public class Room extends Model<Room> {

    private String id;
    private String floor_id;
    private String name;
    private String number;
    private String describe;
    private String master_id;
    private Timestamp create_date;
    private Timestamp modify_date;

    public void setId(String id) {
        this.id = id;
    }

    public void setFloor_id(String floor_id) {
        this.floor_id = floor_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setMaster_id(String master_id) {
        this.master_id = master_id;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public void setModify_date(Timestamp modify_date) {
        this.modify_date = modify_date;
    }

    public String getId() {
        return id;
    }

    public String getFloor_id() {
        return floor_id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getDescribe() {
        return describe;
    }

    public String getMaster_id() {
        return master_id;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public Timestamp getModify_date() {
        return modify_date;
    }
}
