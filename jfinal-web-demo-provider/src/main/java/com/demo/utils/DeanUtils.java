package com.demo.utils;

import com.demo.entity.ResponseEntity;
import com.jfinal.kit.PropKit;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class DeanUtils {

    public static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");

    public static String getRandom(int bound) {
        return String.valueOf(new Random().nextInt(bound));
    }

    public static String getSecretId(EncryptionType type) {
        return MD5Util.encryption(type+df.format(new Date())+getRandom(10000));
    }

    public static Timestamp getTimeStamp() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public static void main(String[] args) {
        System.out.println(getTimeStamp());
    }

    public static ResponseEntity sendRequest(Map<String, Object> params, String url) {
        String result = null;
        try {
            result = HttpUtil.sendRequest(JSONUtil.toJSONString(params), url, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONUtil.parse2Bean(result, ResponseEntity.class);
    }

    public static boolean myBatchSave(String sql) {
        Connection conn = null;
        Statement stat = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            PropKit.use("play.properties");
            conn = DriverManager.getConnection(PropKit.get("jdbc.url"), PropKit.get("jdbc.user"), PropKit.get("jdbc.pass"));
            stat = conn.createStatement();
            return !stat.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
