package com.mountain.po.admin;

import javax.persistence.*;
import java.util.Date;

/**
 * @author porridge
 * @version V1.0
 * @Title: AccountRole.java
 * @Package com.slarts.po
 * @Description: TODO 系统－用户角色
 * @date 2016年10月28日 上午9:45:22
 */
@Entity
@Table(name = "mitaotv_account_roles")
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountRoleId;
    private Integer accountId;//用户id
    private Integer roleId;//角色id
    private Date createdAt;
    private Date updatedAt;

    public AccountRole() {
        super();
    }

    public AccountRole(Integer accountId, Integer roleId) {
        super();
        this.accountId = accountId;
        this.roleId = roleId;
        Date date = new Date();
        this.updatedAt = date;
        this.createdAt = date;
    }

    public Integer getAccountRoleId() {
        return accountRoleId;
    }

    public void setAccountRoleId(Integer accountRoleId) {
        this.accountRoleId = accountRoleId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
