package com.h2ord.corp.api.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;

/**
 * web层用到的工具类
 *
 */
public class WebUtil {
    /**
     * 设置返回字符串
     * @param response
     * @param flag 成功还是失败，
     * @param msg 提示消息
     * @return
     */
    public static String setResponseStr(HttpServletResponse response, boolean flag, String msg){
        JSONObject responseMsg = new JSONObject();//返回的消息
        responseMsg.put("flag", flag);
        responseMsg.put("msg", msg);
        if (flag == false) {
            response.setStatus(500);
        } else {
            response.setStatus(200);
        }
        return responseMsg.toString();
    }
    /**
     * 将对象转换成字符串
     * @param object
     * @return
     */
    public static String objectToString(Object object){
        ObjectMapper mapper = new ObjectMapper();
        JSONObject res=null;
        try {
            res = new JSONObject(mapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    /**
     * 判断请求的josn是否全
     * @param fields 必须包含的字段数组
     * @param jsonBdy
     * @return
     */
    public static boolean ifJsonBdyWhole(String[] fields,JSONObject jsonBdy){
        for(int i=0;i<fields.length;i++){
            if(!jsonBdy.has(fields[i])){
                return false;
            }
        }
        return true;
    }
}
