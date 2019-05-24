package com.demo.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class SessionUtil {

    /**
     * @Title: createSessionId
     * @Description:根据以下参数生成自定义登录态id--sessionId, 以openid为key,sessionId为值存入redis缓存
     * @param
     * @return JSONObject
     * @author SSY
     * @date 2018年8月28日 下午5:03:02
     */
    public String createSessionId(String openId, String sessionKey) {
//        String sessionId = MD5.encrypt(sessionKey);//自定义sessionId
//        CustomerSession cs = new CustomerSession();
//        cs.setCustomerId(customer.getCustomerId());
//        cs.setOpenId(openid);
//        cs.setSessionKey(sessionKey);
//        cs.setIsSeller(customer.getIsSeller());
//        cs.setHeadImgSrc(customer.getHeadImgSrc());
//        cs.setNickName(customer.getNickName());
//        cs.setName(customer.getName());
//        cs.setIdentityCard(customer.getIdentityCard());
//        redisTemplate.opsForValue().set(sessionId, cs, 3600L, TimeUnit.SECONDS);//有效期一小时吧
        String sessionId = DigestUtils.md5Hex(sessionKey);
        Secrets.jedis.setex(sessionId, 3600, openId);
        return sessionId;
    }

    public String getOpenid(String sessionId) {
        Object object = Secrets.jedis.get(sessionId);
        if (object!=null) {
            return String.valueOf(object);
        }
        return null;
    }


}
