package com.h2ord.corp.api.web.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chy on 14-10-14.
 */
@Service
public class WorkRealm extends AuthorizingRealm {





    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        Long id=Long.valueOf((String) getAvailablePrincipal(principalCollection));
        List<String> roles = new ArrayList<String>();
//        User user = service.get(id);
//        if (user != null) {
////            roles.add("admin");
//        } else {
//            throw new AuthorizationException();
//        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 增加角色
        info.addRoles(roles);
        info.addStringPermission("demo:add");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if(authenticationToken instanceof UsernamePasswordToken) {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            StringBuffer passwordBuffer = new StringBuffer();
            passwordBuffer.append(token.getPassword());
//            User user = service.loginByEmail(token.getUsername(), passwordBuffer.toString());
//            if (user != null) {
//                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getId().toString(), user.getPassword(), user.getEmail());
//                return info;
//            }

        }
        return null;
    }
}
