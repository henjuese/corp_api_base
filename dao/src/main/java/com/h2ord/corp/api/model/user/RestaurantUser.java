package com.h2ord.corp.api.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.h2ord.corp.api.model.BaseObject;
import com.h2ord.corp.api.model.corp.Corp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chy on 15/7/29.
 */
@Entity
@Table(name = "restaurant_user")
@JsonIgnoreProperties(value = {"password"})
public class RestaurantUser extends BaseObject{
    @Id
    @Column(unique = true, nullable = false,length = 11)
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false,name = "corp_id")
    private Corp corp;

    @Column(length = 48)
    private String name;

    @Column(name = "hire_date")
    @JsonProperty("hire_date")
    private Date hireDate;

    @Column(length = 16)
    private String loginid;

    @Column(length = 32)
    private String password;

    @Column(columnDefinition = "TINYINT default 0",length = 4)
    private int flag;

    @Column(columnDefinition = "TINYINT default 0",length = 4)
    private int level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Corp getCorp() {
        return corp;
    }

    public void setCorp(Corp corp) {
        this.corp = corp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantUser)) return false;

        RestaurantUser that = (RestaurantUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
