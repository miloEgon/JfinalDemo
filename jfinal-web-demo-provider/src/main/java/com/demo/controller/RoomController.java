package com.demo.controller;

import com.demo.entity.room.Room;
import com.demo.service.RoomService;
import com.demo.utils.DeanUtils;
import com.demo.utils.EncryptionType;
import com.demo.utils.MD5Util;
import com.jfinal.core.ActionKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RoomController extends BaseController {

    RoomService service = new RoomService();

    @ActionKey("/room/insertRoom")
    public void insertRoom() {
        Map map = getJson2Bean(Map.class, getInputStreamData());
        List<Room> list = new ArrayList<>();

        Room room1 = new Room();
        room1.setId(MD5Util.encryption(EncryptionType.room_id+DeanUtils.df.format(new Date())+DeanUtils.getRandom(10000)));
        room1.setNumber("501");
        room1.setMaster_id(getHeader("SID"));
        room1.setFloor_id(String.valueOf(map.get("floor_id")));
        room1.setCreate_date(DeanUtils.getTimeStamp());
        room1.setModify_date(DeanUtils.getTimeStamp());

        Room room2 = new Room();
        room2.setId(MD5Util.encryption(EncryptionType.room_id+DeanUtils.df.format(new Date())+DeanUtils.getRandom(10000)));
        room2.setNumber("502");
        room2.setMaster_id(getHeader("SID"));
        room2.setFloor_id(String.valueOf(map.get("floor_id")));
        room2.setCreate_date(DeanUtils.getTimeStamp());
        room2.setModify_date(DeanUtils.getTimeStamp());

        Room room3 = new Room();
        room3.setId(MD5Util.encryption(EncryptionType.room_id+DeanUtils.df.format(new Date())+DeanUtils.getRandom(10000)));
        room3.setNumber("503");
        room3.setMaster_id(getHeader("SID"));
        room3.setFloor_id(String.valueOf(map.get("floor_id")));
        room3.setCreate_date(DeanUtils.getTimeStamp());
        room3.setModify_date(DeanUtils.getTimeStamp());

        Room room4 = new Room();
        room4.setId(MD5Util.encryption(EncryptionType.room_id+DeanUtils.df.format(new Date())+DeanUtils.getRandom(10000)));
        room4.setNumber("504");
        room4.setMaster_id(getHeader("SID"));
        room4.setFloor_id(String.valueOf(map.get("floor_id")));
        room4.setCreate_date(DeanUtils.getTimeStamp());
        room4.setModify_date(DeanUtils.getTimeStamp());

        list.add(room1);
        list.add(room2);
        list.add(room3);
        list.add(room4);

        Object save = service.save(list);
        OK(save);
    }

    @ActionKey("/room/countRooms")
    public void countRooms() {
        List<String> floors = new ArrayList<>();
        floors.add("\"09849f1b00adedae57e018ea56c1c1eb\"");
        floors.add("\"034344209a08060b8b64455cc4eb9d36\"");
        floors.add("\"8cb5e516240b94c257393b4fbbca5e66\"");
        floors.add("\"266beb415c2e4fe7b810a1a32d2aedf5\"");

        Object rooms = service.countRooms(floors);
        renderJson(rooms);
    }

}
