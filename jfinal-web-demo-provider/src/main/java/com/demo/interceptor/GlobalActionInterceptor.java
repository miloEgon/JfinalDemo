package com.demo.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class GlobalActionInterceptor implements Interceptor {

    public void intercept(Invocation inv) {
        System.out.println("Before GlobalActionInterceptor Invoke");
        inv.invoke();
        System.out.println("After GlobalActionInterceptor Invoke");
    }
}
