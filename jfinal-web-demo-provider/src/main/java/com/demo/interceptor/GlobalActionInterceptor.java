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
        String rd_session = controller.getHeader("rd_session");//拦截请求头中的自定义登录态
        logger.info("拦截到的自定义登录态："+rd_session);
        /*if (null == obj) {
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
        }*/
        if ( StringUtils.isEmpty(rd_session) ) {
            controller.doResult(Secrets.error_status, "Session为空", null);
        } else {
            /*Object obj = cache.get(rd_session);
            logger.info(obj.toString());
            controller.doResult(Secrets.success_status, Secrets.success_msg, obj);*/
            inv.invoke();
        }
        logger.info("After GlobalActionInterceptor Invoke -- "+service.justDuIt());
    }
}
