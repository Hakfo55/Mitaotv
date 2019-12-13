package com.mountain.po.admin;

import javax.persistence.*;
import java.util.Date;

/**
 * @author porridge
 * @version V1.0
 * @Title: RoleFun.java
 * @Package com.slarts.po
 * @Description: TODO 系统－角色功能
 * @date 2016年10月28日 上午9:44:41
 */
@Entity
@Table(name = "mitaotv_role_funs")
public class RoleFun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleFunId;
    private Integer roleId;
    private Integer funId;
    private Date createdAt;
    private Date updatedAt;

    public RoleFun() {
        super();
    }

    public RoleFun(Integer roleId, Integer funId) {
        super();
        this.roleId = roleId;
        this.funId = funId;
        Date date = new Date();
        this.createdAt = date;
        this.updatedAt = date;
    }

    public Integer getRoleFunId() {
        return roleFunId;
    }

    public void setRoleFunId(Integer roleFunId) {
        this.roleFunId = roleFunId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
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
