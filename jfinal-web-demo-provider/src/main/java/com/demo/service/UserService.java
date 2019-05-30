package com.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.constant.WXConstant;
import com.demo.entity.WeChatUser;
import com.demo.exception.ApplicationException;
import com.demo.utils.KCode;
import com.demo.utils.KHttp;
import com.jfinal.kit.StrKit;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {

    private static final ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
    private static final String OPENID = "openid:";

    public void loginCheck(String code) {
        if( StrKit.isBlank(code) ) {
            throw new ApplicationException(-1,"请求参数无效!",null);
        }
    }

    public Object loginService(String code) {
        String url = WXConstant.getUrl()+code;
        //发送请求致微信服务器，获取open_id和session_key
        String result = null;
        try {
            result = KHttp.get(url);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationException(-1,"获取数据异常，请稍候重试!",null);
        }
        JSONObject json = JSON.parseObject(result);
        String openId = json.getString("openid");
        if(StrKit.isBlank(openId)) {//参数错误
            throw new ApplicationException(-1,"参数错误!",null);
        }
        String key = KCode.GUID();
        /**
         * 如果从微信服务器换回open_id和session_key,便生成
         * uuid返回给小程序，小程序每次携带该参数。拦截器每次校验，
         * 如果小程序用户绑定了系统授权的手机号后，uuid对应的value为true。
         */
        map.put(key,false);
        DeviceOperationService.cacheOperationInfoById(key, false, false);
        map.put(OPENID+key,openId);
        DeviceOperationService.cacheOperationInfoById(OPENID+key, openId, false);

        json = new JSONObject();
        json.put("result", key);
        return json;
    }

    public void bindPhoneCheck(String phone, String timestamp, String authKey) {
        if(StrKit.isBlank(timestamp)) {
            throw new ApplicationException(-1, "X-Timestamp(时间戳)参数有误", null);
        }

        long nowTime = Long.parseLong(timestamp);
        if((new Date().getTime() - nowTime) > 60000) {
            throw new ApplicationException(-1, "X-Timestamp(时间戳)已过期，有效时间1分钟", null);
        }

        if(StrKit.isBlank(phone)) {
            throw new ApplicationException(-1, "手机号有误", null);
        }

        if(StrKit.isBlank(authKey)) {
            throw new ApplicationException(-1, "X-Auth参数有误", null);
        }

        Boolean result = (Boolean) map.get(authKey);
        if(result == null) {
            result = (Boolean) DeviceOperationService.getOperationInfoById(authKey);
            if(result == null) {
                throw new ApplicationException(-1, "X-Auth无效", null);
            }
        }
    }

    public void bindPhoneService(String phone, String authKey) {
        List<WeChatUser> weChatUsers =  new WeChatUser().setPhone(phone).select().fetch();
        if(weChatUsers == null || weChatUsers.isEmpty()) {
            throw new ApplicationException(-1, "您的手机号还未授权，请联系管理员", null);
        }

        map.put(authKey, true);
        DeviceOperationService.cacheOperationInfoById(authKey, true, false);

        String openId = (String) map.get(OPENID+authKey);
        if(StrKit.isBlank(openId)) {
            openId = (String) DeviceOperationService.getOperationInfoById(OPENID+authKey);
        }

        for(WeChatUser user : weChatUsers){
            user.setOpenId(openId).update();
        }

    }
}
