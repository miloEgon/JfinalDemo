package com.demo.controller;

import com.demo.entity.User;
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

    public void find() {
        /*String json = getRawData();
        User user = FastJson.getJson().parse(json, User.class);*/

        Integer pageNum = getParaToInt("pageNum");
        Integer pageSize = getParaToInt("pageSize");
        String select = "select *";
        String sql = "from user where id > ? and id < ?";
        Page<User> users = User.dao.paginate(pageNum, pageSize, select, sql,1,9);
        Page<Record> userPage = Db.paginate(pageNum, pageSize, select, sql, 1,9);
        System.out.println(users+"--"+userPage);
        doResult(ResultCode.success, ResultMsg.find_success, json.toJson(userPage));
    }


}
