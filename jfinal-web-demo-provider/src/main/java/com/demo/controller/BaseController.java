package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.jfinal.core.Controller;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaseController extends Controller {

    public void doResult(Object code, String message, Object data) {
        result(code, message, data);
    }

    protected void result(Object code, String message, Object data) {
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("message", message);
        obj.put("data", data);
        renderJson(obj);
    }

    /**
     * 获取数据方法，某些情况能用
     * application/x-www-form-urlencoded不能使用
     * multipart/form-data不能使用
     *
     * @return
     */
    public String getInputStreamData() {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getRequest().getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public <T> T getJson2Bean(Class<T> clazz, String info) {
        return JSON.parseObject(info, clazz, Feature.UseBigDecimal);
    }

    /*public Record getArgsRecord(){
        String jsonStr = HttpKit.readData(getRequest());
        System.out.println("接收的JSON参数："+jsonStr);
        if(StrKit.notBlank(jsonStr)){
            @SuppressWarnings("unchecked")
            Map<String,Object> ls = FastJson.getJson().parse(jsonStr, Map.class);
            Record r = new Record().setColumns(ls);
            System.out.println("转换为Record后的JSON参数："+r.toJson());
            return r;
        } else {
            return new Record();
        }
    }*/

}
