package com.demo.controller;

import com.demo.utils.CommonUtil;
import com.demo.utils.Secrets;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MiniProController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(MiniProController.class);

    private static Cache cache = Redis.use("bbs");

    /**
     * 首次登录
     */
    public void firstLogin() {
        //获取前端请求的code
        String code = getPara(0);
        //登录凭证校验接口获取session_key和openid
        Map<Object, Object> map = getOpenId(code);
        if ( "1".equals(map.get("status")) && "ok".equals(map.get("msg")) ) {
            //生成rd_session(自定义登录态)
            String rd_session = DigestUtils.md5Hex( String.valueOf(map.get("openid")).concat(String.valueOf(map.get("session_key"))) );
            //以rd_session为key,session_key+openid为value,将自定义登录态存入Redis
            cache.set(rd_session, map);
            //业务验证



            //返回自定义登录态
            doResult(1,"ok", rd_session);
        } else {
            doResult(map.get("status"), String.valueOf(map.get("msg")), null);
        }
    }

    /**
     * 请求微信接口服务器获取openId和session_key
     * @param code
     * @return
     */
    public Map<Object, Object> getOpenId(String code) {
        String status = "1";
        String msg = "ok";
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
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
