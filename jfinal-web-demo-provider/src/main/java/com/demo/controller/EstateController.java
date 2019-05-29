package com.demo.controller;

import com.demo.entity.estate.EstateSaveBean;
import com.demo.service.EstateService;
import com.demo.utils.DeanUtils;
import com.demo.utils.Secrets;
import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 房产Controller
 */
public class EstateController extends BaseController {

    EstateService service = new EstateService();

    private static final Logger logger = LoggerFactory.getLogger(EstateController.class);

    @ActionKey("/house/findEstates")
    public void findEstates() {
        Map bean = getJson2Bean(Map.class, getInputStreamData());
        resultRecord(Secrets.success_status, Secrets.success_msg, service.findEstates(bean));
    }

    @ActionKey("/house/findEstateById")
    public void findEstateById() {
        Map bean = getJson2Bean(Map.class, getInputStreamData());
        resultRecord(Secrets.success_status, Secrets.success_msg, service.findEstateById(bean));
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
