package com.demo.controller;

import com.demo.entity.PageEntity;
import com.demo.entity.ReqBean;
import com.demo.entity.estate.EstateSaveBean;
import com.demo.entity.room.RoomReqBean;
import com.demo.exception.ApplicationException;
import com.demo.service.EstateService;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.StrKit;

import java.util.Map;

/**
 * 房产Controller
 */
public class EstateController extends BaseController {

    EstateService service = new EstateService();

    /**
     * 分页展示房产列表
     */
    @ActionKey("/house/findEstates")
    public void findEstates() {
        PageEntity bean = getJson2Bean(PageEntity.class, getInputStreamData());
        bean.setAuthKey(getHeader("X-Auth"));
        JSR303Validator(bean);
        OK(service.findEstates(bean));
}

    /**
     * 获取安装师傅名下所有房产
     */
    @ActionKey("/house/getEstateList")
    public void getEstateList() {
        OK(service.getEstateList(getHeader("X-Auth")));
    }

    /**
     * 打开房产详情
     */
    @ActionKey("/house/findEstateById")
    public void findEstateById() {
        ReqBean bean = getJson2Bean(ReqBean.class, getInputStreamData());
        bean.setAuthKey(getHeader("X-Auth"));
        JSR303Validator(bean);
        OK(service.findEstateById(bean));
    }

    /**
     * 根据楼层ID获取房间列表
     */
    @ActionKey("/house/findRooms")
    @Clear
    public void findRooms() {
        RoomReqBean bean = getJson2Bean(RoomReqBean.class, getInputStreamData());
        bean.setAuthKey(getHeader("X-Auth"));
        JSR303Validator(bean);
        OK(service.findRooms(bean));
    }







    @ActionKey("/house/insertEstate")
    public void insertEstate() {
        EstateSaveBean bean = getJson2Bean(EstateSaveBean.class, getInputStreamData());
        bean.setMaster_id(getHeader("SID"));
        JSR303Validator(bean);
        OK(service.batchSave(bean));
    }





}
