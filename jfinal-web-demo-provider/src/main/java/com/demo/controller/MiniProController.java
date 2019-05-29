package com.demo.controller;

import com.demo.entity.WeChatEntity;
import com.demo.utils.CommonUtil;
import com.demo.utils.Secrets;
import com.jfinal.core.ActionKey;
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
    @ActionKey("/login")
    public void login() {
        String code = getJson2Bean(WeChatEntity.class, getInputStreamData()).getCode();
        logger.info(code);
        //登录凭证校验接口获取session_key和openid
        Map<Object, Object> map = getOpenId(code);
        if ( Secrets.success_status.equals(map.get("status")) && Secrets.success_msg.equals(map.get("msg")) ) {
            //生成rd_session(自定义登录态)
            String rd_session = DigestUtils.md5Hex( String.valueOf(map.get("openid")).concat(String.valueOf(map.get("session_key"))) );
            //以rd_session为key,session_key+openid为value,将自定义登录态存入Redis
            cache.set(rd_session, map);
            //业务验证

            //返回自定义登录态
            doResult(Secrets.success_status, Secrets.success_msg, rd_session);
        } else {
            doResult(Secrets.error_status, String.valueOf(map.get("msg")), null);
        }
    }

    /**
     * 小程序用户session保存
     */
    @ActionKey("/setSession")
    public void setSession() {
        WeChatEntity bean = getJson2Bean(WeChatEntity.class, getInputStreamData());
        logger.info(bean.getOpenid()+"--"+bean.getSession_key());
        Map<Object, Object> map = new HashMap<Object, Object>();
        String rd_session = DigestUtils.md5Hex( bean.getOpenid().concat(bean.getSession_key()) );
        logger.info(rd_session);
        map.put("openid", bean.getOpenid());
        map.put("session_key", bean.getSession_key());
        String set = cache.set(rd_session, map);
        doResult(Secrets.success_status, Secrets.success_msg, set);
    }

    /**
     * 请求微信接口服务器获取openId和session_key
     * @param code
     * @return
     */
    public static Map<Object, Object> getOpenId(String code) {
        Integer status = Secrets.success_status;
        String msg = Secrets.success_msg;
        Map<Object, Object> map = new HashMap<Object, Object>();
        try {
            if (StringUtils.isBlank(code)) {
                status = Secrets.error_status;//失败状态
                msg = "code为空";
            } else {
                String requestUrl = Secrets.WX_URL.replace("APPID", Secrets.appId).replace("SECRET", Secrets.appSecret).replace("JSCODE", code);
                logger.info(requestUrl);
                // 发起GET请求获取凭证
                JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
                if (jsonObject != null) {
                    try {
                        map.put("openid", jsonObject.getString("openid"));
                        map.put("session_key", jsonObject.getString("session_key"));
                    } catch (JSONException e) {
                        status = Secrets.error_status;
                        msg = "获取token失败";
                    }
                } else {
                    status = Secrets.error_status;
                    msg = "code无效";
                }
            }
            logger.info(msg);
            map.put("status", status);
            map.put("msg", msg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return map;
    }

    /*public static void main(String[] args) {
        Map<Object, Object> map = getOpenId(Secrets.code);
        logger.info(map.toString());
    }*/



}
