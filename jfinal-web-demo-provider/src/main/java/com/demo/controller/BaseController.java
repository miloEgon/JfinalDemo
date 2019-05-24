package com.demo.controller;

import com.jfinal.core.Controller;
import net.sf.json.JSONObject;

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

}
