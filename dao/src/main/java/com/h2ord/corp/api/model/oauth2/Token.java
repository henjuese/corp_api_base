package com.h2ord.corp.api.model.oauth2;

import com.h2ord.corp.api.model.user.RestaurantUser;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chy on 14-10-16.
 */
@Entity
@Table(name = "corp_token")
public class Token {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "auth_code" )
    private String authCode;

    @Column(name="create_date",unique = true, nullable = false)
    private Date createDate;

    @Column(name = "auth_expiration_date",unique = true, nullable = false)
    private Date authExpirationDate;

    @Column(name = "token_expiration_date")
    private Date tokenExpirationDate;

    @Column(name = "refresh_token")
    private String refreshToken;

    @ManyToOne
    @JoinColumn(nullable = false,name = "user_id")
    private RestaurantUser user;

    @ManyToOne
    @JoinColumn(nullable = false,name = "client_id")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Date getCreateDate() {
        return new Date(createDate.getTime());
    }

    public void setCreateDate(Date createDate) {
        this.createDate = new Date(createDate.getTime());
    }

    public Date getAuthExpirationDate() {
        return new Date(authExpirationDate.getTime());
    }

    public void setAuthExpirationDate(Date authExpirationDate) {
        this.authExpirationDate = new Date(authExpirationDate.getTime());
    }

    public RestaurantUser getUser() {
        return user;
    }

    public void setUser(RestaurantUser user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getTokenExpirationDate() {
        return new Date(tokenExpirationDate.getTime());
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setTokenExpirationDate(Date tokenExpirationDate) {
        this.tokenExpirationDate = new Date(tokenExpirationDate.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;

        Token token = (Token) o;

        if (id != null ? !id.equals(token.id) : token.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
