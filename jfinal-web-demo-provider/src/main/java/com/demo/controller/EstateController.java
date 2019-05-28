package com.demo.controller;

import com.demo.entity.Estate;
import com.demo.entity.req_bean.PageRequestBean;
import com.demo.service.EstateService;
import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Page;

/**
 * 房产Controller
 */
public class EstateController extends BaseController {

    EstateService service = new EstateService();

    @ActionKey("/house/findEstates")
    public void findEstates() {
        String info = getInputStreamData();
        PageRequestBean bean = getJson2Bean(PageRequestBean.class, info);
        JSR303Validator(bean);
        Page<Estate> estates = service.findEstates(bean);
        OK(estates);
    }




}
