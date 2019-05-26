package com.demo.interceptor;

import com.demo.controller.BaseController;
import com.demo.service.AccountService;
import com.jfinal.aop.Inject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

public class GlobalActionInterceptor implements Interceptor{

    private final static Cache cache = Redis.use("bbs");

    @Inject
    AccountService service;

    public void intercept(Invocation inv) {
        BaseController controller = (BaseController)inv.getController();
        System.out.println("Before GlobalActionInterceptor Invoke"+service.justDuIt());
        String rd_session = controller.getHeader("rd_session");//拦截请求头中的自定义登录态
        System.out.println(rd_session);
        Object obj = cache.get(rd_session);
        if (null == obj) {
            controller.doResult(0,"会话过期",null);
        } else {
            Map<Object, Object> map = (Map<Object, Object>) obj;
            String openid = String.valueOf(map.get("openid"));
            String session_key = String.valueOf(map.get("session_key"));
            if ( DigestUtils.md5Hex(openid.concat(session_key)).equals(rd_session) ) {
                inv.invoke();
            } else {
                controller.doResult(0,"身份认证失败", null);
            }
        }
        System.out.println("After GlobalActionInterceptor Invoke"+service.justDuIt());
    }
}
