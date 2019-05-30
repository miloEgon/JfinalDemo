package com.demo.controller;

import com.demo.service.UserService;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;

public class UserController extends BaseController {

    private UserService service = new UserService();

    /**
     * 微信小程序登录认证Action
     */
    @ActionKey("/user/login")
    @Clear
    public void login() {
        String code = getPara();//获取微信小程序携带的code
        service.loginCheck(code);
        Object data = service.loginService(code);
        OK(data);
    }

    /**
     * 微信小程序绑定手机号
     */
    @ActionKey("/user/bind_phone")
    public void bindPhone() {
        String phone = getPara();
        String timestamp = getHeader("X-Timestamp");
        String authKey = getHeader("X-Auth");
        service.bindPhoneCheck(phone, timestamp, authKey);
        service.bindPhoneService(phone, authKey);
        OK();
    }

}
