package com.demo.controller;

import com.demo.entity.PageRequestBean;
import com.demo.entity.estate.Estate;
import com.demo.entity.estate.EstateSaveBean;
import com.demo.service.EstateService;
import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 房产Controller
 */
public class EstateController extends BaseController {

    EstateService service = new EstateService();

    private static final Logger logger = LoggerFactory.getLogger(EstateController.class);

    @ActionKey("/house/findEstates")
    public void findEstates() {
        String info = getInputStreamData();
        PageRequestBean bean = getJson2Bean(PageRequestBean.class, info);
        JSR303Validator(bean);
//        Page<Estate> estates = service.findEstates(bean);
        Page<Record> estates = service.findEstates(bean);
        Record record = new Record();
        record.set("list",estates.getList());
        record.set("pageNumber",estates.getPageNumber());
        record.set("pageSize",estates.getPageSize());
        record.set("totalPage",estates.getTotalPage());
        record.set("totalRow",estates.getTotalRow());

        renderText(record.toJson());
    }

    @ActionKey("/house/insertEstate")
    public void insertEstate() {
        EstateSaveBean bean = getJson2Bean(EstateSaveBean.class, getInputStreamData());
        JSR303Validator(bean);
        bean.setMaster_id(getHeader("SID"));
        Object obj = service.batchSave(bean);
        OK(obj);
    }

    @ActionKey("/house/findEstateById")
    public void findEstateById() {
        Map map = getJson2Bean(Map.class, getInputStreamData());
        String estate_id = String.valueOf(map.get("estate_id"));
//        logger.info(estate_id);
//        Estate estate = service.findEstateById(estate_id);
//        OK(estate);
//        String json = service.findEstateById(estate_id).toJson();
        Record record = new Record();
        record.set("code",0);
        record.set("message","ok");
        record.set("data", service.findEstateById(estate_id));

        renderText(record.toJson());
    }




}
