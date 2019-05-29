package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import net.sf.json.JSONObject;
import com.demo.exception.ApplicationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

public class BaseController extends Controller {

    protected static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    protected static Validator validator = factory.getValidator();

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

    public void resultRecord(Object code, String message, Object data) {
        Record record = new Record();
        record.set("code", code);
        record.set("message", message);
        record.set("data", data);
        renderText(record.toJson());
    }

    public void OK() {
        result(0, "ok", null);
    }

    public void OK(Object data) {
        result(0, "ok", data);
    }

    public void ERROR(int code, String message) {
        result(code, message, null);
    }

    public void ERROR(int code, String message, Object data) {
        result(code, message, data);
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

    /**
     * 使用hibernate-validator实现的JSR303验证model
     */
    public <M> void JSR303Validator(M model) {
        Set<ConstraintViolation<M>> constraintViolations = validator.validate(model);
        if (constraintViolations.size() == 0)
            return;
        for (ConstraintViolation<M> c : constraintViolations) {
            throw new ApplicationException(c.getMessage(), 10000, null);
        }
    }

}
