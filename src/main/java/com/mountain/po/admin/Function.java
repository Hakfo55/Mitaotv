package com.mountain.po.admin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author porridge
 * @version V1.0
 * @Title: Function.java
 * @Package com.slarts.po
 * @Description: TODO 系统－菜单
 * @date 2016年10月28日 上午9:40:29
 */
@Entity
@Table(name = "mitaotv_functions")
public class Function {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer funId;
    @NotNull
    private String name;//菜单名
    private Integer parentId;//父级id
    private String ico;//小图标
    private String url;//连接地址
    @Column(name = "fun_method")
    private String method;//请求方法类型
    @NotNull
    private Integer funType;//菜单类型 1菜单  2按钮
    @Column(name = "fun_sort")
    private Integer sort;//排序
    @Column(name = "fun_show")
    private Integer show;//菜单列表是否显示 1显示 2隐藏
    private Date createdAt;
    private Date updatedAt;

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getFunType() {
        return funType;
    }

    public void setFunType(Integer funType) {
        this.funType = funType;
    }

    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
