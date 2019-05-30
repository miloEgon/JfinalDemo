package com.demo.constant;

public class WXConstant {

    public final static String AUTHURL = "https://api.weixin.qq.com/sns/jscode2session";

    public final static String APPID = "wxb293e104076401fc";

    public final static String SECRET = "47099fa82e776cc3991f9b8ce0206fe5";

    public static String getUrl() {
        return AUTHURL+"?appid="+APPID+"&secret="+SECRET+"&grant_type=authorization_code&js_code=";
    }
}
