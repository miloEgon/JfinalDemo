package com.demo.service;

import com.jfinal.aop.Clear;

public class AccountService {

    @Clear
    public String justDuIt() {
        String text = "GO,GO,GO";
        System.out.println(text);
        return text;
    }
}
