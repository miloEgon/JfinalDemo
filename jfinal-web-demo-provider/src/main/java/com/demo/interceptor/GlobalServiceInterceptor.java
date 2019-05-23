package com.demo.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class GlobalServiceInterceptor implements Interceptor {

    public void intercept(Invocation inv) {
        System.out.println("Before GlobalServiceInterceptor Invoke");
        inv.invoke();
        System.out.println("After GlobalServiceInterceptor Invoke");
    }
}
