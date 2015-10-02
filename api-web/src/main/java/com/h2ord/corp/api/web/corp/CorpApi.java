package com.h2ord.corp.api.web.corp;

import com.h2ord.corp.api.model.corp.Corp;
import com.h2ord.corp.api.model.oauth2.Token;
import com.h2ord.corp.api.service.corp.CorpService;
import com.h2ord.corp.api.service.oauth2.TokenService;
import com.h2ord.corp.api.util.json.ResultUtil;
import com.h2ord.corp.api.web.util.WebUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chy on 15/8/1.
 */
@RestController
@RequestMapping("/api/corp")
public class CorpApi {

    @Autowired
    TokenService tokenService;

    @Autowired
    CorpService corpService;

    @RequestMapping(produces = "application/json; charset=utf-8")
    public String get(HttpServletRequest request) {
        try {
            String accessToken = request.getAttribute("accessToken").toString();
            Token token = tokenService.getToken(accessToken);
            JSONObject result = new JSONObject();
            result.put("corp", new JSONObject(WebUtil.objectToString(token.getUser().getCorp())));
            return ResultUtil.result(0, result.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.result(500, "{'msg:'" + e.getMessage() + "}");
        }


    }

    @RequestMapping(method = RequestMethod.PUT,produces = "application/json; charset=utf-8")
    public String update(@RequestBody String body, HttpServletRequest request){
        JSONObject object = new JSONObject(body);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        JSONObject result = new JSONObject();
        try {
//            Date openTime =  simpleDateFormat.parse(object.getString("business_time"));
//            Date closeTime =  simpleDateFormat.parse(object.getString("close_time"));
            String businessTime = object.getString("business_time");
            String contactPhone = object.getString("contact_phone");


            String accessToken = request.getAttribute("accessToken").toString();
            Token token = tokenService.getToken(accessToken);
            Corp corp = token.getUser().getCorp();
            corp.setBusinessTime(businessTime);
            corp.setContactPhone(contactPhone);
            corp = corpService.saveOrUpdate(corp);

            result.put("corp",new JSONObject(WebUtil.objectToString(corp)));
            return ResultUtil.result(0,result.toString());
        } catch (Exception e) {

            e.printStackTrace();
            result = new JSONObject();
            result.put("msg",e.getMessage());
            return ResultUtil.result(500,result.toString());
        }


    }

}
