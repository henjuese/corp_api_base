package com.h2ord.corp.api.model.corp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.h2ord.corp.api.model.BaseObject;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chy on 15/7/31.
 */

@Entity
@Table(name = "corporation")
public class Corp extends BaseObject{

    @Id
    @Column(unique = true, nullable = false,length = 11)
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String city;

    @Column(name = "contact_phone")
    @JsonProperty("contact_phone")
    private String contactPhone;

    //开始营业时间
    @Column(name = "open_time")
    @JsonProperty("open_time")
    private Date openTime;

    //结束营业时间
    @Column(name = "close_time")
    @JsonProperty("close_time")
    private Date closeTime;

    @Column(name="business_time")
    @JsonProperty("business_time")
    private String businessTime;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Corp)) return false;

        Corp corp = (Corp) o;

        if (id != null ? !id.equals(corp.id) : corp.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
