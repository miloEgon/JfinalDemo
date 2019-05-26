package com.demo.controller;

import com.jfinal.core.Controller;
import com.jfinal.json.FastJson;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Record;
import net.sf.json.JSONObject;

import java.util.Map;

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
