package com.demo.interceptor;

import com.demo.controller.BaseController;
import com.demo.service.AccountService;
import com.demo.utils.Secrets;
import com.jfinal.aop.Inject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalActionInterceptor implements Interceptor{

    private final static Cache cache = Redis.use("bbs");

    private final static Logger logger = LoggerFactory.getLogger(GlobalActionInterceptor.class);

    @Inject
    AccountService service;

    public void intercept(Invocation inv) {
        BaseController controller = (BaseController)inv.getController();
        logger.info("Before GlobalActionInterceptor Invoke -- "+service.justDuIt());
        String authKey = controller.getHeader("X-Auth");//拦截请求头中的自定义登录态
        logger.info("拦截到的自定义登录态："+authKey);
        Object openid = cache.get("openid:"+authKey);
        if ( StringUtils.isEmpty(authKey) ) {
            controller.ERROR(Secrets.error_status, "Session为空");
        } else if (null == openid) {
            controller.ERROR(Secrets.error_status, "Session无效");
        } else {
            logger.info(openid.toString());
            inv.invoke();
            logger.info("After GlobalActionInterceptor Invoke -- "+service.justDuIt());
        }
    }
}
