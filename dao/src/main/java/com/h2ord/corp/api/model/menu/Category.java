package com.h2ord.corp.api.model.menu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.h2ord.corp.api.model.BaseObject;
import com.h2ord.corp.api.model.corp.Corp;

import javax.persistence.*;

/**
 * Created by chy on 15/8/1.
 */
@Entity
@Table(name = "menu_category")
@JsonIgnoreProperties(value = {"corp"})
public class Category extends BaseObject{

    @Id
    @Column(unique = true, nullable = false, length = 11)
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false,name = "corp_id")
    private Corp corp;

    @Column(length = 64)
    private String name;

    @Column(name = "short_name", length = 16)
    @JsonProperty("short_name")
    private String shortName;

    @Column(columnDefinition = "TINYINT default 0",length = 4)
    private int type;

    @Column(length = 4)
    private char code;

    @Column(length = 32)
    private String icon;

    @Column(name = "enable_flag",columnDefinition = "TINYINT default 0",length = 4)
    @JsonProperty("enable_flag")
    private int enableFlag; //是否启用：1：启用；0：停用

    @Column(name = "display_flag",columnDefinition = "TINYINT default 0",length = 4)
    @JsonProperty("display_flag")
    private int displayFlag; //在app点餐栏的显示开关：1：显示；0：不显示

    @Column(name = "app_display_flag",columnDefinition = "TINYINT default 0",length = 4)
    @JsonProperty("app_display_flag")
    private int appDisplayFlag; //客户端APP中是否显示；1：显示；0：不显示

    @Column(name = "show_order",columnDefinition = "TINYINT default 0",length = 4)
    @JsonProperty("show_order")
    private int showOrder;

    @Column(length = 128)
    private String memo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Corp getCorp() {
        return corp;
    }

    public void setCorp(Corp corp) {
        this.corp = corp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public char getCode() {
        return code;
    }

    public void setCode(char code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(int enableFlag) {
        this.enableFlag = enableFlag;
    }

    public int getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(int displayFlag) {
        this.displayFlag = displayFlag;
    }

    public int getAppDisplayFlag() {
        return appDisplayFlag;
    }

    public void setAppDisplayFlag(int appDisplayFlag) {
        this.appDisplayFlag = appDisplayFlag;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (id != null ? !id.equals(category.id) : category.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
