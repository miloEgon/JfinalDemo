package com.demo.controller;

import com.demo.entity.floor.FloorSaveBean;
import com.demo.service.FloorService;
import com.jfinal.core.ActionKey;

public class FloorController extends BaseController {

    FloorService service = new FloorService();

    @ActionKey("/floor/insertFloor")
    public void insertFloor() {
        FloorSaveBean bean = getJson2Bean(FloorSaveBean.class, getInputStreamData());
        bean.setMaster_id(getHeader("SID"));
        JSR303Validator(bean);
        OK(service.save(bean));
    }

    @ActionKey("/floor/insertRoom")
    public void insertRoom() {
        FloorSaveBean bean = getJson2Bean(FloorSaveBean.class, getInputStreamData());
        bean.setMaster_id(getHeader("SID"));
        JSR303Validator(bean);
        OK(service.saveRooms(bean));
    }

}
