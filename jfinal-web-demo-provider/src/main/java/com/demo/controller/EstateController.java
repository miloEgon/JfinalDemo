package com.demo.controller;

import com.demo.entity.PageEntity;
import com.demo.entity.estate.EstateSaveBean;
import com.demo.exception.ApplicationException;
import com.demo.service.EstateService;
import com.demo.utils.Secrets;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.StrKit;

import java.util.Map;

/**
 * 房产Controller
 */
public class EstateController extends BaseController {

    EstateService service = new EstateService();

    @ActionKey("/house/findEstates")
    public void findEstates() {
        PageEntity bean = getJson2Bean(PageEntity.class, getInputStreamData());
        bean.setAuthKey(getHeader("rd_session"));
        JSR303Validator(bean);
        OK(service.findEstates(bean));
//        resultRecord(Secrets.success_status, Secrets.success_msg, service.findEstates(bean));
    }

    @ActionKey("/house/findEstateById")
    public void findEstateById() {
        Map bean = getJson2Bean(Map.class, getInputStreamData());
        if (StrKit.isBlank((String) bean.get("estate_id")))
            throw new ApplicationException("房产ID为空",-1,null);
        OK(service.findEstateById(bean));
//        resultRecord(Secrets.success_status, Secrets.success_msg, service.findEstateById(bean));
    }

    /**
     * 根据楼层ID获取房间列表
     */
    @ActionKey("/house/findRooms")
    public void findRooms() {
        Map bean = getJson2Bean(Map.class, getInputStreamData());
        if (StrKit.isBlank((String) bean.get("floor_id")))
            throw new ApplicationException("楼层ID为空",-1,null);
        OK(service.findRooms(bean));
//        resultRecord(Secrets.success_status, Secrets.success_msg, service.findRooms(bean));
    }







    @ActionKey("/house/insertEstate")
    public void insertEstate() {
        EstateSaveBean bean = getJson2Bean(EstateSaveBean.class, getInputStreamData());
        JSR303Validator(bean);
        bean.setMaster_id(getHeader("SID"));
        Object obj = service.batchSave(bean);
        OK(obj);
    }





}