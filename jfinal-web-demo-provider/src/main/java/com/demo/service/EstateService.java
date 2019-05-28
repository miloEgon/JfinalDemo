package com.demo.service;

import com.demo.entity.Estate;
import com.demo.entity.req_bean.PageRequestBean;
import com.jfinal.plugin.activerecord.Page;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EstateService {

    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");

    public Page<Estate> findEstates(PageRequestBean bean) {

        Integer pageNum = bean.getPageNum();
        Integer pageSize = bean.getPageSize();
        String select = "select *";
        String sql = "from tb_estate";
        Page<Estate> estates = Estate.dao.paginate(pageNum, pageSize, select, sql);
        for (Estate estate : estates.getList()) {
            estate.setCreateDate(df.format(estate.getCreateDate()));
            estate.setModifyDate(df.format(estate.getModifyDate()));
        }

        return estates;

    }

}
