package com.h2ord.corp.api.util.json;

import org.json.JSONObject;

/**
 * Created by chy on 15/7/29.
 */
public class ResultUtil {

    public static String result(int retCode,String result){
        JSONObject results = new JSONObject();
        results.put("ret_code",retCode);
        results.put("results",new JSONObject(result));
        return results.toString();


    }

}
