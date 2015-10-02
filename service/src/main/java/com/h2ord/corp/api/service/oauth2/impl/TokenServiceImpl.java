package com.h2ord.corp.api.service.oauth2.impl;

import com.h2ord.corp.api.dao.oauth2.TokenDao;
import com.h2ord.corp.api.model.oauth2.Token;
import com.h2ord.corp.api.model.user.RestaurantUser;
import com.h2ord.corp.api.service.impl.AbstractServiceImpl;
import com.h2ord.corp.api.service.oauth2.ClientService;
import com.h2ord.corp.api.service.oauth2.TokenService;
import com.h2ord.corp.api.service.user.RestaurantUserService;
import com.h2ord.corp.api.util.oauth2.OauthConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by chy on 14-10-16.
 */
@Service
@Transactional(readOnly = true)
public class TokenServiceImpl extends AbstractServiceImpl<Token, Long> implements TokenService {

    @Autowired
    public TokenServiceImpl(TokenDao dao) {
        super(dao);
    }

    // TODO 修改登录验证
    @Autowired
    private RestaurantUserService userService;

    @Autowired
    private ClientService clientService;

    @Transactional
    @Override
    public Token createAuthCode(String loginid, String authCode, String clientCode) {
        Token token = new Token();
        token.setAuthCode(authCode);
        token.setCreateDate(new Date());
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.MINUTE, OauthConstant.auth_code_expiration_date);
        token.setAuthExpirationDate(rightNow.getTime());
        token.setUser(userService.getUserByLoginid(loginid));
        token.setClient(clientService.getClientByCode(clientCode));
        token = saveOrUpdate(token);
        return token;
    }

    @Transactional
    @Override
    public Token createAuthCode(RestaurantUser user, String authCode, String clientCode) {
        Token token = new Token();
        token.setAuthCode(authCode);
        token.setCreateDate(new Date());
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.MINUTE, OauthConstant.auth_code_expiration_date);
        token.setAuthExpirationDate(rightNow.getTime());
        token.setUser(user);
        token.setClient(clientService.getClientByCode(clientCode));
        token = saveOrUpdate(token);
        return token;
    }

    @Override
    @Transactional
    public Token createAccessToken(String authCode, String accessToken, String refreshToken, String clientId) {
        String hql = "from " + Token.class.getName() + " as t where t.authCode=:authCode and t.client.clientId=:clientId";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("authCode", authCode);
        conditionMap.put("clientId", clientId);
        List<Token> result = findByHql(hql, conditionMap);
        Token token = null;
        if (result != null && !result.isEmpty()) {
            token = result.get(0);
            token.setAccessToken(accessToken);
            token.setRefreshToken(refreshToken);
            Calendar rightNow = Calendar.getInstance();
            rightNow.add(Calendar.DAY_OF_YEAR, OauthConstant.access_token_expiration_date);
            token.setTokenExpirationDate(rightNow.getTime());
            saveOrUpdate(token);
        }
        return token;
    }

    @Override
    @Transactional
    public Token updateAccessToken(String authCode, String accessToken, String clientId) {
        String hql = "from " + Token.class.getName() + " as t where t.authCode=:authCode and t.client.clientId=:clientId";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("authCode", authCode);
        conditionMap.put("clientId", clientId);
        List<Token> result = findByHql(hql, conditionMap);
        Token token = null;
        if (result != null && !result.isEmpty()) {
            token = result.get(0);
            token.setAccessToken(accessToken);
            Calendar rightNow = Calendar.getInstance();
            rightNow.add(Calendar.DAY_OF_YEAR, OauthConstant.access_token_expiration_date);
            token.setTokenExpirationDate(rightNow.getTime());
            saveOrUpdate(token);
        }
        return token;
    }

    @Override
    public Token getToken(String loginid, String clientId) {
        String hql = "from " + Token.class.getName() + " as t where t.user.loginid=:loginid and t.client.clientId=:clientId";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("loginid", loginid);
        conditionMap.put("clientId", clientId);
        List<Token> result = findByHql(hql, conditionMap);
        Token token = null;
        if (result != null && !result.isEmpty()) {
            token = result.get(0);
        }
        return token;
    }

    @Override
    @Transactional
    public Token updateAuthCode(Token token, String authCode) {
        token.setAuthCode(authCode);
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.MINUTE, OauthConstant.auth_code_expiration_date);
        token.setAuthExpirationDate(rightNow.getTime());
        return saveOrUpdate(token);
    }

    /**
     * 验证auth code是否有效
     *
     * @param authCode
     * @return
     */
    @Override
    public boolean checkAuthCode(String authCode) {
        boolean check = false;
        String hql = "from " + Token.class.getName() + " as t where t.authCode=:authCode and t.authExpirationDate > :date";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("authCode", authCode);
        conditionMap.put("date", new Date());
        List<Token> result = findByHql(hql, conditionMap);
        if (result != null && !result.isEmpty()) {
            check = true;
        }

        return check;
    }

    /**
     * 验证access token是否有效
     *
     * @param accessToken
     * @return
     */
    @Override
    public boolean checkAccessToken(String accessToken) {
        boolean check = false;
        String hql = "from " + Token.class.getName() + " as t where t.accessToken=:accessToken and t.tokenExpirationDate > :date";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("accessToken", accessToken);
        conditionMap.put("date", new Date());
        List<Token> result = findByHql(hql, conditionMap);
        if (result != null && !result.isEmpty()) {
            check = true;
        }
        return check;
    }

    @Override
    @Transactional
    public Token getToken(String token) {
        String hql = "from Token as t where t.accessToken=:accessToken";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("accessToken", token);
        List<Token> result = findByHql(hql, conditionMap);
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public Token refreshToken(String accessToken, String newToken,String refreshToken) {
        Token token = getToken(accessToken);
        if (token != null && token.getRefreshToken().equals(refreshToken)) {
            Calendar rightNow = Calendar.getInstance();
            rightNow.add(Calendar.DAY_OF_YEAR, OauthConstant.access_token_expiration_date);
            token.setTokenExpirationDate(rightNow.getTime());
            token.setAccessToken(newToken);
            return saveOrUpdate(token);
        } else {
            return null;
        }

    }
}
