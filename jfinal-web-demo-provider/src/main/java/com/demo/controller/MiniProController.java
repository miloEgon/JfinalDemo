package com.demo.controller;

import com.demo.utils.CommonUtil;
import com.demo.utils.Secrets;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiniProController extends BaseController {

    @Clear
    public void index() {
        //获取前端请求的code
        String code = getPara(0);
        //登录凭证校验接口获取session_key和openid
        Map<Object, Object> map = getOpenId(code);
        if ( "1".equals(map.get("status")) && "ok".equals(map.get("msg")) ) {
            //生成rd_session(自定义登录态)
            String rd_session = DigestUtils.md5Hex(String.valueOf(map.get("openid")).concat(String.valueOf(new Date().getTime()))
                    .concat(String.valueOf(map.get("session_key"))));
            //以rd_session为key,session_key+openid为value,将自定义登录态存入Redis
            Cache cache = Redis.use("bbs");
            cache.hmset(rd_session, map);
            //返回自定义登录态
            doResult(1,"ok", rd_session);
        } else {
            doResult(map.get("status"), String.valueOf(map.get("msg")), null);
        }
    }

    public boolean validate(String rd_session) {
        //根据rd_session查找合法的session_key以及openid
        Cache cache = Redis.use("bbs");
        List list = cache.hmget(rd_session);


        return false;
    }


    public Map<Object, Object> getOpenId(String code) {
        String status = "1";
        String msg = "ok";
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        Logger logger = Logger.getLogger(MiniProController.class);
        Map<Object, Object> map = new HashMap<Object, Object>();

        try {
            if (StringUtils.isBlank(code)) {
                status = "0";//失败状态
                msg = "code为空";
            } else {
                String requestUrl = WX_URL.replace("APPID", Secrets.appId).replace("SECRET", Secrets.appSecret).replace("JSCODE", code);
                logger.info(requestUrl);
                // 发起GET请求获取凭证
                JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
                if (jsonObject != null) {
                    try {
                        map.put("openid", jsonObject.getString("openid"));
                        map.put("session_key", jsonObject.getString("session_key"));
                    } catch (JSONException e) {
                        // 获取token失败
                        status = "0";
                        msg = "code无效";
                    }
                } else {
                    status = "0";
                    msg = "code无效";
                }
            }
            map.put("status", status);
            map.put("msg", msg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return map;
    }

}
