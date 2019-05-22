package com.demo.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class AdminController extends Controller {

    @Clear
    public void index() {
        renderText("I'm the admin...");
    }

    @ActionKey("/admin/login")
    public void login() {
        render("login.html");
    }

    public String test() {
        return "login.html";
    }

    // 未配置Method级别拦截器，但会被Class级别拦截器DemoInterceptor所拦截
    public void show() {
        renderText("method show");
    }
}
