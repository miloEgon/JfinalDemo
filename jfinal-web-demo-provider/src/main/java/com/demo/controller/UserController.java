package com.demo.controller;

import com.demo.entity.User;
import com.demo.utils.PageUtil;
import com.demo.utils.ResultCode;
import com.demo.utils.ResultMsg;
import com.jfinal.aop.Clear;
import com.jfinal.json.Json;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class UserController extends BaseController {

    private static final Json json =  Json.getJson();

    @Clear
    public void insert() {
        boolean save = new User().set("id", 1L).set("username", "Jack").set("password", "246789").save();
        if (save) renderText("save success");
        else renderText("save error");
    }

    public void delete() {
        boolean flag = User.dao.deleteById(getPara(0));
        if (flag) renderText("delete success");
        else renderText("delete error");
    }

    public void findById() {
        PageUtil bean = getJson2Bean(PageUtil.class, getInputStreamData());
        System.out.println(bean.getId());
        User user = User.dao.findById(bean.getId());
        doResult(ResultCode.success, ResultMsg.find_success, json.toJson(user));
    }

    public void findByPage() {
        PageUtil bean = getJson2Bean(PageUtil.class, getInputStreamData());

        /*Integer pageNum = getParaToInt("pageNum");
        Integer pageSize = getParaToInt("pageSize");*/
        Integer pageNum = bean.getPageNum();
        Integer pageSize = bean.getPageSize();
        String select = "select *";
        String sql = "from user where id > ? and id < ?";
        /*Page<User> users = User.dao.paginate(pageNum, pageSize, select, sql,1,9);
        System.out.println(users);*/
        Page<Record> users = Db.paginate(pageNum, pageSize, select, sql, 1,9);
//        System.out.println(users);
        doResult(ResultCode.success, ResultMsg.find_success, json.toJson(users));
    }


}
