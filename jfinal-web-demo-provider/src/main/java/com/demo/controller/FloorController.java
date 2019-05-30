package com.demo.controller;

import com.demo.entity.floor.FloorSaveBean;
import com.demo.service.FloorService;
import com.jfinal.core.ActionKey;

public class FloorController extends BaseController {

    FloorService service = new FloorService();

    @ActionKey("/floor/insertFloor")
    public void insertFloor() {
        FloorSaveBean bean = getJson2Bean(FloorSaveBean.class, getInputStreamData());
        JSR303Validator(bean);
        bean.setMaster_id(getHeader("SID"));
        Object obj = service.save(bean);
        OK(obj);
    }





}
