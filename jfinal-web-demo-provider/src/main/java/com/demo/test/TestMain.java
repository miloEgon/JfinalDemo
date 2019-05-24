package com.demo.test;

import com.demo.service.AccountService;
import com.jfinal.aop.Aop;

public class TestMain {

    static AccountService service = Aop.get(AccountService.class);

    public static void main(String[] args) {
        System.out.println(service.justDuIt());
    }
}
