package com.demo.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class AAA implements Interceptor {

    public void intercept(Invocation inv) {
        System.out.println("Before AAA Invoke");
        inv.invoke();
        System.out.println("After AAA Invoke");
    }
}
