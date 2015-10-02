package com.h2ord.corp.api.web.menu;

import com.h2ord.corp.api.model.oauth2.Token;
import com.h2ord.corp.api.service.menu.category.CategoryService;
import com.h2ord.corp.api.service.oauth2.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chy on 15/8/1.
 */
@RestController
@RequestMapping
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/v1/api/menu/category")
    public String get(@RequestBody String body,HttpServletRequest request){
        String accessToken = request.getAttribute("accessToken").toString();
        Token token = tokenService.getToken(accessToken);
        try {

        } catch (Exception e){

        }
        return null;

    }



}
