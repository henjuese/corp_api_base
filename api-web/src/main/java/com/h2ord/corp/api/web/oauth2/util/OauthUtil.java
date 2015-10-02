package com.h2ord.corp.api.web.oauth2.util;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

/**
 * Created by chy on 14-10-17.
 */
public class OauthUtil {
    public static String createAuthCode() throws OAuthSystemException {
        OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        String authorizationCode = oauthIssuerImpl.authorizationCode();
        return authorizationCode;
    }
    public static String createAccessToken() throws OAuthSystemException {
        OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        String accessToken = oauthIssuerImpl.accessToken();
        return accessToken;

    }
    public static String createRefreshToken() throws OAuthSystemException {
        OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        String refreshToken = oauthIssuerImpl.refreshToken();
        return refreshToken;
    }
}
