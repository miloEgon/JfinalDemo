package com.demo.controller;

import com.jfinal.aop.Clear;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;

public class RedisController extends BaseController {

    private final static Cache cache = Redis.use("bbs");

    private final String openid = "ZKFDAFJK005ASKFDAS02";

    private final String session_key = "SESSIONDFASJKLFD1120";

    @Clear
    public void setKey() {
        String rd_session = DigestUtils.md5Hex(openid.concat(session_key));
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("openid",openid);
        map.put("session_key",session_key);
//        cache.setex(rd_session, 60, map);
        cache.set(rd_session,map);
        doResult(1,"保存成功", rd_session);
    }

    public void hello() {
        /*String rd_session = getHeader("rd_session");
        System.out.println("get it "+rd_session);
        Object obj = cache.get(rd_session);
        if (null == obj) {
            doResult(0,"会话过期",null);
        } else {
            Map<Object, Object> map = (Map<Object, Object>) obj;
            String openid = String.valueOf(map.get("openid"));
            String session_key = String.valueOf(map.get("session_key"));
            if ( DigestUtils.md5Hex(openid.concat(session_key)).equals(rd_session) ) {
                doResult(1,"认证成功", null);
            } else {
                doResult(0,"身份认证失败", null);
            }
        }*/
        doResult(1,"Hello World", null);
    }
}
