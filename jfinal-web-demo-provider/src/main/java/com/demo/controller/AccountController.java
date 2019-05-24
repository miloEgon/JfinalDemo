package com.demo.controller;

import com.demo.service.AccountService;
import com.demo.service.Service;
import com.jfinal.aop.Aop;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

public class AccountController extends Controller {

    @Inject
    AccountService accountService;

    Service service = Aop.get(Service.class);

    public void index() {
        renderJson(service.getMap());
    }




}
