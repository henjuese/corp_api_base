package com.h2ord.corp.api.service.oauth2;


import com.h2ord.corp.api.model.oauth2.Token;
import com.h2ord.corp.api.model.user.RestaurantUser;
import com.h2ord.corp.api.service.AbstractService;

/**
 * Created by chy on 14-10-16.
 */

public interface TokenService extends AbstractService<Token, Long> {

    Token createAuthCode(String email, String authCode, String clientCode);

    Token createAuthCode(RestaurantUser user, String authCode, String clientCode);

    Token createAccessToken(String authCode, String accessToken, String refreshToken, String clientCode);

    Token updateAccessToken(String authCode, String accessToken,  String clientCode);

    Token getToken(String email, String clientCode);

    Token updateAuthCode(Token token, String authCode);

    /**
     * 验证auth code是否有效
     *
     * @param authCode
     * @return
     */
    boolean checkAuthCode(String authCode);

    /**
     * 验证access token是否有效
     *
     * @param accessToken
     * @return
     */
    boolean checkAccessToken(String accessToken);

    Token getToken(String token);

    Token refreshToken(String accessToken,String newToken,String refreshToken);


}
