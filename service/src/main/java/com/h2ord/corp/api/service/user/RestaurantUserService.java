package com.h2ord.corp.api.service.user;

import com.h2ord.corp.api.model.user.RestaurantUser;
import com.h2ord.corp.api.service.AbstractService;

/**
 * Created by chy on 15/7/30.
 */
public interface RestaurantUserService extends AbstractService<RestaurantUser,Integer> {

    RestaurantUser getUserByLoginid(String loginid);

    RestaurantUser login(String loginid,String password);



}
