package com.h2ord.corp.api.service.user.impl;

import com.h2ord.corp.api.dao.user.RestaurantUserDao;
import com.h2ord.corp.api.model.user.RestaurantUser;
import com.h2ord.corp.api.service.impl.AbstractServiceImpl;
import com.h2ord.corp.api.service.user.RestaurantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chy on 15/7/30.
 */
@Service
@Transactional(readOnly = true)
public class RestaurantUserServiceImpl extends AbstractServiceImpl<RestaurantUser,Integer> implements RestaurantUserService {

    @Autowired
    public RestaurantUserServiceImpl(RestaurantUserDao dao) {
        super(dao);
    }

    @Override
    public RestaurantUser getUserByLoginid(String loginid) {
        String hql = "from "+RestaurantUser.class.getName() + " as u where u.loginid=:loginid";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("loginid", loginid);
        List<RestaurantUser> result = getDao().findByHql(hql, conditionMap);
        RestaurantUser user = null;
        if (result != null && !result.isEmpty()) {
            user = result.get(0);
        }
        return user;
    }

    @Override
    public RestaurantUser login(String loginid, String password) {
        String hql = "from "+RestaurantUser.class.getName() + " as u where u.loginid=:loginid and u.password = :password";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("loginid", loginid);
        conditionMap.put("password", password);
        List<RestaurantUser> result = getDao().findByHql(hql, conditionMap);
        RestaurantUser user = null;
        if (result != null && !result.isEmpty()) {
            user = result.get(0);
        }
        return user;
    }
}
