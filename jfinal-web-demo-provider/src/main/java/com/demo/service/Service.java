package com.demo.service;

import java.util.HashMap;
import java.util.Map;

public class Service {

    public Map<Object, Object> getMap() {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put(1, "a");
        map.put(2, "b");
        return map;
    }
}
