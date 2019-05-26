package com.demo.controller;

import com.demo.entity.User;
import com.jfinal.core.Controller;

public class UserController extends Controller {

    public void index() {
        renderText("this is user index");
    }

    public void insert() {
        boolean save = new User().set("username", "James").set("password", "246789").save();
        if (save) renderText("save success");
        else renderText("save error");
    }

    public void batchInsert() {
        for (int i = 0; i < 10; i++) {
            new User().set("username","Tom").set("password","123456").save();
        }
        renderText("save success");
    }

    public void delete() {
        boolean flag = User.dao.deleteById(getPara(0));
        if (flag) renderText("delete success");
        else renderText("delete error");
    }

}
