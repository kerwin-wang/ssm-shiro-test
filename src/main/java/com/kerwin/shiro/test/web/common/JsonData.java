package com.kerwin.shiro.test.web.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: JsonData
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-03-13 13:34
 */
@Getter
@Setter
public class JsonData
{
    private boolean ret;

    private String msg;

    private Object data;

    public JsonData(boolean ret){
        this.ret = ret;
    }

    public static JsonData success(){
        return new JsonData(true);
    }

    public static JsonData success(Object object){
        JsonData jsonData = new JsonData(true);
        jsonData.setData(object);
        return jsonData;
    }

    public static JsonData fail(String msg){
        JsonData jsonData = new JsonData(false);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public Map<String,Object> toMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ret",ret);
        hashMap.put("msg",msg);
        hashMap.put("data", data);
        return hashMap;
    }
}
