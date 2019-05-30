package com.demo.controller;

import com.demo.service.DeviceOperationService;
import com.demo.utils.KCode;
import com.jfinal.core.ActionKey;

public class RedisController extends BaseController {

    @ActionKey("/redis/setKey")
    public void setKey() {
        String openid = "onTbc4oKkd1OyaW3-4U3mqaM_Z-A";
        String key = KCode.GUID();
        DeviceOperationService.cacheOperationInfoById("openid:"+key, openid, false);
        OK(key);
    }

    @ActionKey("/redis/getKey")
    public void getKey() {
        /*Map bean = getJson2Bean(Map.class, getInputStreamData());
        String param = (String) bean.get("param");*/
        String param = getHeader("rd_session");
        String openid = (String) DeviceOperationService.getOperationInfoById("openid:" + param);
        OK(openid);
    }









//    @Clear
//    public void setKey() {
//        String rd_session = DigestUtils.md5Hex(openid.concat(session_key));
//        Map<Object, Object> map = new HashMap<Object, Object>();
//        map.put("openid",openid);
//        map.put("session_key",session_key);
////        cache.setex(rd_session, 60, map);
//        cache.set(rd_session,map);
//        doResult(1,"保存成功", rd_session);
//    }

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
