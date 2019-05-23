package com.demo.controller;

import com.demo.service.AccountService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

public class AccountController extends Controller {

    @Inject
    AccountService service;

    public void index() {
        renderText(service.justDuIt());
    }

}
