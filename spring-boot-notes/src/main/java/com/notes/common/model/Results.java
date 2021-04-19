package com.notes.common.model;

import java.util.HashMap;
import java.util.Map;

public class Results extends HashMap<String, Object> {


    private static final long serialVersionUID = 158L;

    public Results() {
        put("s", "ok");
    }


    public static Results error(String errorMsg) {
        Results r = new Results();
        r.put("s", "error");
        r.put("errmsg", errorMsg);
        return r;
    }

    public static Results ok(Object val) {
        Results r = new Results();
        r.put("s", "ok");
        r.put("d",val);
        return r;
    }

    public static Results okMap(Map<String,Object> map) {
        Results r = new Results();
        r.putAll(map);
        return r;
    }


    public static Results ok() {
        return new Results();
    }

    @Override
    public Results put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}