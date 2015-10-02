package com.h2ord.corp.api.web.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chy on 15/7/30.
 */
@RestController
@RequestMapping("/api/user/")
public class RestaurantUserApi {

    @RequestMapping("/:id")
    public String detail(@RequestBody String messageJson){
        return null;

    }

}
