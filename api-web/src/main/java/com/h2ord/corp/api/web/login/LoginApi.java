package com.h2ord.corp.api.web.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.h2ord.corp.api.model.oauth2.Token;
import com.h2ord.corp.api.model.user.RestaurantUser;
import com.h2ord.corp.api.service.oauth2.ClientService;
import com.h2ord.corp.api.service.oauth2.TokenService;
import com.h2ord.corp.api.service.user.RestaurantUserService;
import com.h2ord.corp.api.util.json.ResultUtil;
import com.h2ord.corp.api.web.oauth2.util.OauthUtil;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by chy on 15/7/31.
 */

@RestController
public class LoginApi {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private RestaurantUserService userService;

    @RequestMapping(value = "/v1/api/login",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public String login(@RequestBody String loginJson, HttpServletResponse response) {
        System.out.println(loginJson);
        JSONObject object = new JSONObject(loginJson);
        JSONObject responseMsg = new JSONObject();

        String loginid = null;
        String password = null;
        String clientId = null;
        String clientSecret = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            loginid = (String) object.get("loginid");
            password = (String) object.get("password");
            clientId = object.getString("client_id");
            clientSecret = object.getString("client_secret");
            RestaurantUser user = userService.login(loginid, password);
            if (user != null) {
                if (clientId != null && clientSecret != null) {
                    if (clientService.checkClient(clientId, clientSecret)) {
                        Token oauthToken = tokenService.getToken(user.getLoginid(), clientId);
                        if (oauthToken == null) {
                            oauthToken = tokenService.createAuthCode(user, OauthUtil.createAuthCode(), clientId);
                            if (tokenService.checkAuthCode(oauthToken.getAuthCode())) {
                                oauthToken = tokenService.createAccessToken(oauthToken.getAuthCode(), OauthUtil.createAccessToken(), OauthUtil.createRefreshToken(), clientId);
                                responseMsg.put("user_info", new JSONObject( mapper.writeValueAsString(user)));
                                responseMsg.put("access_token", oauthToken.getAccessToken());
                                responseMsg.put("exp_time",oauthToken.getTokenExpirationDate().getTime());
                                responseMsg.put("refresh_token",oauthToken.getRefreshToken());
                            }
                        } else {
                            if (tokenService.checkAccessToken(oauthToken.getAccessToken())) {
                                responseMsg.put("user_info", new JSONObject( mapper.writeValueAsString(user)));
                                responseMsg.put("access_token", oauthToken.getAccessToken());
                                responseMsg.put("exp_time",oauthToken.getTokenExpirationDate().getTime());
                                responseMsg.put("refresh_token",oauthToken.getRefreshToken());

                            } else {
                                oauthToken = tokenService.updateAuthCode(oauthToken, OauthUtil.createAuthCode());
                                if (tokenService.checkAuthCode(oauthToken.getAuthCode())) {
                                    oauthToken = tokenService.updateAccessToken(oauthToken.getAuthCode(), OauthUtil.createAccessToken(), clientId);
                                    responseMsg.put("user_info", new JSONObject( mapper.writeValueAsString(user)));
                                    responseMsg.put("access_token", oauthToken.getAccessToken());
                                    responseMsg.put("exp_time",oauthToken.getTokenExpirationDate().getTime());
                                    responseMsg.put("refresh_token",oauthToken.getRefreshToken());

                                }
                            }
                        }
                    } else {
                        responseMsg.put("msg", "client不存在或者验证错误");
                        return ResultUtil.result(404,responseMsg.toString());
                    }
                } else {
                    responseMsg.put("msg", "client不存在或者验证错误");
                    return ResultUtil.result(404, responseMsg.toString());
                }
            } else {
                responseMsg.put("msg", "用户名或密码错误");
                return ResultUtil.result(403, responseMsg.toString());

            }


        } catch (Exception e) {
            e.printStackTrace();
            responseMsg.put("msg", e.getMessage());
            return ResultUtil.result(403, responseMsg.toString());
        }

        return ResultUtil.result(0, responseMsg.toString());
    }

    @RequestMapping(value = "/v1/api/logout",method = RequestMethod.GET,produces = "application/json; charset=utf-8" )
    public String logout (HttpServletRequest request) throws UnsupportedEncodingException {
        String accessToken = request.getAttribute("accessToken").toString();
        Token token = tokenService.getToken(accessToken);
        JSONObject msg = new JSONObject();
        if(token != null){
            token.setTokenExpirationDate(new Date());
            tokenService.saveOrUpdate(token);
            msg.put("msg","退出成功");
            return ResultUtil.result(0,msg.toString());
        }
        else{
            msg.put("msg","该用户不存在或者已经退出");
            return ResultUtil.result(0,msg.toString());
        }
    }

    @RequestMapping(value = "/v1/api/refresh",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public String refresh(@RequestBody String body){
        JSONObject object = new JSONObject(body);
        JSONObject responseMsg = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        //TODO 是否需要新token要想一下
        Token token = null;
        try {
            token = tokenService.refreshToken(object.getString("access_token"), OauthUtil.createAccessToken(),object.getString("refresh_token"));
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
        if(token != null ){
            try {
                responseMsg.put("user_info", new JSONObject( mapper.writeValueAsString(token.getUser())));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            responseMsg.put("access_token", token.getAccessToken());
            responseMsg.put("exp_time",token.getTokenExpirationDate().getTime());
            return ResultUtil.result(0,responseMsg.toString());

        }else{
            responseMsg.put("msg","内部错误");
            return ResultUtil.result(500,responseMsg.toString());
        }

    }


}
