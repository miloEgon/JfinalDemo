package com.demo.controller;

import com.demo.entity.User;
import com.demo.utils.ResultCode;
import com.demo.utils.ResultMsg;
import com.jfinal.aop.Clear;
import com.jfinal.json.Json;
import com.jfinal.plugin.activerecord.Page;

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
//        Record r = getArgsRecord();
//        Long id = Long.valueOf(r.getStr("id"));
//        System.out.println("获取到的参数："+id);

        Integer pageNum = getParaToInt("pageNum");
        Integer pageSize = getParaToInt("pageSize");
//        String sql = "select * from user where id = "+id;
        Page<User> users = User.dao.paginate(pageNum, pageSize, "select *", "from user");
//        List<User> users = User.dao.find(sql);
//        List<Record> users = Db.find(sql);
        System.out.println(users);
        doResult(ResultCode.success, ResultMsg.find_success, json.toJson(users));

        /*String json = getRawData();
        renderJson(json);*/
       /* Long id = Long.valueOf(getPara("id"));
        String name = getPara("name");
        renderText(id.toString()+"--"+name);*/
        /*User.dao.findById();*/
    }


}
