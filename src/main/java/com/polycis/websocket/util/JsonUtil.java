package com.polycis.websocket.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author : Wenyu Zhou
 * @version : v1.0
 * @date : 2019/7/3
 * description : json类型的判断
 */
public class JsonUtil {

    public static boolean isJsonObject(String jsonStr){
        Object object;
        try {
            object = JSON.parse(jsonStr);
        } catch (Exception e) {
            return false;
        }
        return object instanceof JSONObject;
    }
    public static boolean isJsonArray(String jsonStr){
        Object object;
        try {
            object = JSON.parse(jsonStr);
        } catch (Exception e) {
            return false;
        }
        return object instanceof JSONArray;
    }
}
